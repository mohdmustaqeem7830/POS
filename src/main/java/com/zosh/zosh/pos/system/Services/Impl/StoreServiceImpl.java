package com.zosh.zosh.pos.system.Services.Impl;

import com.zosh.zosh.pos.system.Domain.StoreStatus;
import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Model.StoreContact;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.StoreDTO;
import com.zosh.zosh.pos.system.Repository.StoreRepository;
import com.zosh.zosh.pos.system.Services.StoreService;
import com.zosh.zosh.pos.system.Services.UserService;
import com.zosh.zosh.pos.system.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final  UserService userService;



    @Override
    public StoreDTO createStore(StoreDTO storeDTO, User user) {

        Store store = StoreMapper.toEntity(storeDTO,user);

        return StoreMapper.toDto(storeRepository.save(store));
    }

    @Override
    public StoreDTO getStoreById(Long id) throws Exception {

        Store store = storeRepository.findById(id).orElseThrow(

                ()-> new Exception("Store Not Found")
        );

        return StoreMapper.toDto(store);
    }

    @Override
    public List<StoreDTO> getAllStores() {
        List<Store> dtos = storeRepository.findAll();
        return dtos.stream().map(StoreMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public Store getStoreByAdmin() throws UserException {

        User admin = userService.getCurrentUser();
        return  storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDTO) throws Exception {
      User currentUser = userService.getCurrentUser();

      Store existing = storeRepository.findByStoreAdminId(id);

      if (existing==null){
          throw  new Exception("Store Not Found");
      }

       existing.setBrand(storeDTO.getBrand());
      existing.setDescription(storeDTO.getDescription());


      if(storeDTO.getStoreType()!=null){
          existing.setStoreType(storeDTO.getStoreType());
      }


      if (storeDTO.getContact()!=null){
          StoreContact storeContact = StoreContact.builder()
                  .address(storeDTO.getContact().getAddress())
                  .phone(storeDTO.getContact().getPhone())
                  .email(storeDTO.getContact().getEmail())
                  .build();

          existing.setContact(storeContact);
      }


      Store updatedStore  = storeRepository.save(existing);

      return StoreMapper.toDto(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws UserException {
        Store store = getStoreByAdmin();

        storeRepository.delete(store);
    }

    @Override
    public StoreDTO getStoreByEmployee() throws UserException {
        User currentUser = userService.getCurrentUser();
        if (currentUser==null){
            throw  new UserException("You don't have permission to access this store");
        }


        return StoreMapper.toDto(currentUser.getStore());
    }

    @Override
    public StoreDTO moderateStore(Long id, StoreStatus storeStatus) throws Exception {
        Store store  = storeRepository.findById(id).orElseThrow(

                () -> new Exception("Store not found")
        );

        store.setStatus(storeStatus);
        Store updateStore= storeRepository.save(store);
        return StoreMapper.toDto(updateStore);
    }
}
