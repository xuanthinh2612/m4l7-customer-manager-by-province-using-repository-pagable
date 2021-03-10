package com.codeGym.service.customer;

import com.codeGym.model.Customer;
import com.codeGym.model.Province;
import com.codeGym.service.IService;

import java.util.List;

public interface ICustomerService extends IService<Customer> {
 List<Customer> findAllByProvince(Province province);
}
