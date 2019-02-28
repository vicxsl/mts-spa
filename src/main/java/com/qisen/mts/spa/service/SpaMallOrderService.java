package com.qisen.mts.spa.service;

import java.util.List;

import com.qisen.mts.common.model.response.CommObjResponse;
import com.qisen.mts.spa.model.entity.SpaMallOrder;

public interface SpaMallOrderService {

	CommObjResponse<List<SpaMallOrder>> save(SpaMallOrder body);

	CommObjResponse<List<SpaMallOrder>> list(SpaMallOrder body);

}
