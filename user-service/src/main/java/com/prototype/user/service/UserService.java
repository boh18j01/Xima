package com.prototype.user.service;

import com.ecommerce.core.models.payments.MakePaymentCommand;
import com.ecommerce.core.models.payments.PaymentProcessedEvent;
import com.ecommerce.core.models.payments.PaymentStatus;
import com.prototype.user.dto.UpdateUserCredit;
import com.prototype.user.dto.CreateUserRequest;
import com.prototype.user.entity.User;
import com.prototype.user.repository.UserRepository;
import com.prototype.user.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    @Autowired
    private KafkaTemplate<String, Object> template;

    public Mono<User> getUser(String id){
        return repository.findById(id);
    }

    public Mono<User> getUserByCardNumber(String cardNumber){
        return repository.findByCardnumberEquals(cardNumber);
    }

    public Mono<User> saveUser(CreateUserRequest request) {
        return repository.insert(AppUtils.createUser(request));
    }




    public Flux<User> getAllUsers() {
        return repository.findAll();
    }



    public Mono<User> updateUserCredit(Mono<UpdateUserCredit> update) {
        return update.flatMap(request -> {
            Mono<User> user = repository.findByCardnumberEquals(request.getCardNumber());
            return user.flatMap(userMap -> {
                var userAvailableCredit = userMap.getAvailableCredit() - request.getAmount();
                userMap.setAvailableCredit(userAvailableCredit);
                return  repository.save(userMap);
            });
        });
    }

    public void processPayment(MakePaymentCommand command) {
        this.getUserByCardNumber(command.getCardnumber()).subscribe(user -> {
           var event =  new PaymentProcessedEvent(command.getPaymentId(), command.getCardnumber(), PaymentStatus.PROCESSING);
           if(user.getAvailableCredit() >= command.getAmount()){
               event.setStatus(PaymentStatus.APPROVED);
               var userAvailableCredit = user.getAvailableCredit() + command.getAmount();
               user.setAvailableCredit(userAvailableCredit);
               repository.save(user).subscribe();
           }else{
               event.setStatus(PaymentStatus.REJECTED);
           }
            this.template.send("payment-process-user-response", event);
        });

    }
}
