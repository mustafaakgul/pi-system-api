package com.example.demo;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class PutSimpleExample {

    static final String URL_UPDATE_EMPLOYEE = "http://localhost:8080/employee";
    static final String URL_EMPLOYEE_PREFIX = "http://localhost:8080/employee";

    public static void main(String[] args) {

        String empNo = "E01";

        Employee updateInfo = new Employee(empNo, "Tom", "Cleck");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<Employee> requestBody = new HttpEntity<>(updateInfo, headers);

        // Send request with PUT method.
        restTemplate.put(URL_UPDATE_EMPLOYEE, requestBody, new Object[] {});

        String resourceUrl = URL_EMPLOYEE_PREFIX + "/" + empNo;

        Employee e = restTemplate.getForObject(resourceUrl, Employee.class);

        if (e != null) {
            System.out.println("(Client side) Employee after update: ");
            System.out.println("Employee: " + e.getEmpNo() + " - " + e.getEmpName());
        }
    }

}
