package provinceservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import provinceservice.model.Province;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends MongoRepository<Province,String> {
    List<Province> findAllByName(String name);
    Optional<Province> findByName(String name);
    
}
