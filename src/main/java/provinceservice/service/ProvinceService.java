package provinceservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import provinceservice.exception.ProvinceAlreadyExistsException;
import provinceservice.exception.ProvinceNotFoundException;
import provinceservice.model.Province;
import provinceservice.repository.ProvinceRepository;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProvinceService {

    private final ProvinceRepository provinceRepository;



    public List<Province> getProvince(String name) {

        if(name==null){
            return provinceRepository.findAll();
        }else{
            return provinceRepository.findAllByName(name);
        }

    }

    public Province createProvince(Province newProvince) {
        Optional<Province> provinceByName= provinceRepository.findByName(newProvince.getName());
            if (provinceByName.isPresent()){
                throw new ProvinceAlreadyExistsException("Province Already Exists With Name: "+newProvince.getName());
            }
               return provinceRepository.save(newProvince);
    }

    public Province getProvinceById(String id) {
        return provinceRepository.findById(id)
                .orElseThrow(()->new ProvinceNotFoundException("Province Not Found With Id: "+id));
    }
    public void deleteProvince(String id) {
        provinceRepository.deleteById(id);
    }
    public void updateProvince(String id, Province newProvince) {
        Province oldProvince=getProvinceById(id);
        oldProvince.setName(newProvince.getName());
        provinceRepository.save(oldProvince);
    }


}
