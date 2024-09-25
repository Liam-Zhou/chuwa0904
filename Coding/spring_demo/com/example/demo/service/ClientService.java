
package com.example.demo.service;

import com.example.demo.model.ServiceA;
import com.example.demo.model.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ServiceA serviceA;
    private ServiceB serviceB;

    // Constructor injection for ServiceA
    @Autowired
    public ClientService(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    // Setter injection for ServiceB
    @Autowired
    @Qualifier("serviceB") // Resolve ambiguity by using Qualifier
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    // Field injection for ServiceB
    @Autowired
    @Qualifier("serviceB")
    private ServiceB anotherServiceB;

    public void execute() {
        System.out.println("Executing Client Service:");
        serviceA.doService();      // Using constructor-injected ServiceA
        serviceB.doService();      // Using setter-injected ServiceB
        anotherServiceB.doService(); // Using field-injected ServiceB
    }
}
