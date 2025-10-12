package com.zosh.zosh.pos.system.Services;

import com.zosh.zosh.pos.system.Domain.StoreStatus;
import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.StoreDTO;

import java.util.List;

public interface StoreService {
    StoreDTO createStore(StoreDTO storeDTO, User User);
    StoreDTO getStoreById(Long id) throws Exception;
    List<StoreDTO> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDTO  updateStore(Long id , StoreDTO storeDTO) throws UserException, Exception;
    void deleteStore(Long id) throws UserException;
    StoreDTO getStoreByEmployee() throws UserException;
    StoreDTO  moderateStore(Long id , StoreStatus storeStatus) throws UserException, Exception;
}
