package com.desafiosantander.buscacep.service;

import com.desafiosantander.buscacep.record.CepRecord;

public interface ICepService {

    CepRecord buscaCep(String zipeCode);
}
