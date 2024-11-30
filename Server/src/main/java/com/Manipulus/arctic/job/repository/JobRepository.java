package com.Manipulus.arctic.job.repository;

import com.Manipulus.arctic.customer.model.Customer;
import com.Manipulus.arctic.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {

    void deleteJobById(Long id);

    Optional<Job> findJobById(Long id);
    Optional<Customer> findCustomerById(long id);
}