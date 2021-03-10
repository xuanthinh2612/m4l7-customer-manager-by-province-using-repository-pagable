package com.codeGym.service.customer;

import com.codeGym.model.Customer;
import com.codeGym.model.Province;
import com.codeGym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.codeGym.repository.ICustomerRepository;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IProvinceService provinceService;

    @Override
    public List<Customer> findALl() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Page<Customer> findALl(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.delete(id);
    }


    @Override
    public List<Customer> findByName(String name) {
        return customerRepository.findCustomerByName(name);
    }

    @Override
    public List<Customer> findAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }
}
