package com.example.demo.model.pic.service;

import com.example.demo.common.base.BaseService;
import com.example.demo.model.pic.repo.PicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PicService extends BaseService {
    @Autowired
    private PicRepo picRepo;
    @Override
    public void init() {
        setBaseRepo(picRepo);
    }

}
