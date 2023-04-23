package provinceservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import provinceservice.exception.ProvinceAlreadyExistsException;
import provinceservice.exception.ProvinceNotFoundException;
import provinceservice.model.Province;
import provinceservice.service.ProvinceService;
import java.util.List;

@RestController
@RequestMapping("/provinces")
@AllArgsConstructor
public class ProvinceController {

    private final ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<List<Province>> getProvinces(@RequestParam(required = false)String name) {

        return new ResponseEntity<>(provinceService.getProvince(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Province> getProvince(@PathVariable String id){

            return new ResponseEntity<>(getProvinceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Province> createProvince(@RequestBody Province newProvince){

        return new ResponseEntity<>(provinceService.createProvince(newProvince),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> getProvince(@PathVariable String id, @RequestBody Province newProvince){
        provinceService.updateProvince(id,newProvince);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvince(@PathVariable String id){
        provinceService.deleteProvince(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Province getProvinceById(String id){
        return provinceService.getProvinceById(id);
    }


    @ExceptionHandler(ProvinceNotFoundException.class)
    public ResponseEntity<String> handleProvinceNotFoundException(ProvinceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProvinceAlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsFoundException(ProvinceAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}

