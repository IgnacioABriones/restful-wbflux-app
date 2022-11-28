package com.mitocode.service.DishServiceImpl;

import com.mitocode.model.Client;
import com.mitocode.repo.IClientRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends CRUDImpl<Client, String> implements IClientService {

    @Autowired
    private IClientRepo repo;

    @Override
    protected IGenericRepo<Client, String> getRepo() {
        return repo;
    }
}
