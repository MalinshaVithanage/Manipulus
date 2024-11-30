package com.Manipulus.arctic.job.service;

import com.Manipulus.arctic.customer.exception.CustomerNotFoundException;
import com.Manipulus.arctic.customer.model.Customer;
import com.Manipulus.arctic.job.exception.JobNotFoundException;
import com.Manipulus.arctic.job.model.Job;
import com.Manipulus.arctic.job.repository.JobRepository;
import com.Manipulus.arctic.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {
    private final com.Manipulus.arctic.job.repository.JobRepository JobRepository;
    private final com.Manipulus.arctic.customer.repository.CustomerRepository customerRepository;
    @Autowired
    public JobService(JobRepository jobRepository , CustomerRepository customerRepository) {
        this.JobRepository = jobRepository;
        this.customerRepository = customerRepository;
    }
    public Customer findCustomerById(Long id) {
        // Find the customer with the given ID in the database
        // Throw a CustomerNotFoundException if the customer is not found
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " was not found"));
    }


    public Job addJob(Job job, Long id) {
        job.setJobCode(UUID.randomUUID().toString());
        job.setCustomer(findCustomerById(id));
        job.setJob_status("Not Complete");
        return JobRepository.save(job);
    }

    public List<Job> findAllJobs() {
        return JobRepository.findAll();
    }

    public Job updateJob(Job job, Long id) {
        job.setCustomer(findCustomerById(id));
        job.setJob_status("Not Complete");
        return JobRepository.save(job);
    }

    @Transactional
    public void deleteJobById(Long id) {
        JobRepository.deleteJobById(id);
    }
}