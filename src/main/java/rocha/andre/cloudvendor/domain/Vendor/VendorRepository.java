package rocha.andre.cloudvendor.domain.Vendor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository <Vendor, Long> {
    boolean existsByName(String name);
}
