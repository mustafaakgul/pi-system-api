package com.example.demo;



import org.springframework.web.client.RestTemplate;

public class DeleteSimpleExample {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        // empNo="E01"
        String resourceUrl = "http://localhost:8080/employee/E01";

        // Send request with DELETE method.
        restTemplate.delete(resourceUrl);

        // Get
        Employee e = restTemplate.getForObject(resourceUrl, Employee.class);

        if (e != null) {
            System.out.println("(Client side) Employee after delete: ");
            System.out.println("Employee: " + e.getEmpNo() + " - " + e.getEmpName());
        } else {
            System.out.println("Employee not found!");
        }
    }

}