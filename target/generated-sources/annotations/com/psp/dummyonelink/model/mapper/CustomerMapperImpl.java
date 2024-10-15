package com.psp.dummyonelink.model.mapper;

import com.psp.dummyonelink.model.dto.CustomerDto;
import com.psp.dummyonelink.model.entity.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-15T14:24:37+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto jpeToDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setFirstName( customer.getFirstName() );
        customerDto.setLastName( customer.getLastName() );
        customerDto.setMobileNumber( customer.getMobileNumber() );
        customerDto.setEmail( customer.getEmail() );
        customerDto.setCnic( customer.getCnic() );
        customerDto.setDateOfBirth( customer.getDateOfBirth() );
        customerDto.setAddress( customer.getAddress() );
        customerDto.setCity( customer.getCity() );
        customerDto.setState( customer.getState() );
        customerDto.setRegisteredDate( customer.getRegisteredDate() );
        customerDto.setCountry( customer.getCountry() );

        return customerDto;
    }

    @Override
    public Customer dtoToJpe(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFirstName( customerDto.getFirstName() );
        customer.setLastName( customerDto.getLastName() );
        customer.setEmail( customerDto.getEmail() );
        customer.setMobileNumber( customerDto.getMobileNumber() );
        customer.setCnic( customerDto.getCnic() );
        customer.setDateOfBirth( customerDto.getDateOfBirth() );
        customer.setAddress( customerDto.getAddress() );
        customer.setCity( customerDto.getCity() );
        customer.setState( customerDto.getState() );
        customer.setCountry( customerDto.getCountry() );
        customer.setRegisteredDate( customerDto.getRegisteredDate() );

        return customer;
    }
}
