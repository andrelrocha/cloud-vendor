package rocha.andre.cloudvendor.domain.Vendor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository <Vendor, Long> {
    boolean existsByName(String name);

    List<Vendor> findByNameAndCity(String name, String city);
}
