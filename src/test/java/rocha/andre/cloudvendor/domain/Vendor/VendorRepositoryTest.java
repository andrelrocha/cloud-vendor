package rocha.andre.cloudvendor.domain.Vendor;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class VendorRepositoryTest {
    @Autowired
    private VendorRepository vendorRepository;
    Vendor vendor;
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Check if Vendor with the same name and city exists, giving same name and city")
    void vendorRepositoryCase1() {
        //given
        var vendor = createVendor("andre", "rua ruosa", "fortaleza");

        //when
        var vendorWithSameStreetAndName = vendorRepository.findByNameAndCity("andre", "fortaleza");

        //then
        assertNotNull(vendorWithSameStreetAndName);
    }

    ////
    private VendorDto vendorData(String name, String street, String city) {
        return new VendorDto(
                name,
                "8599999999",
                street,
                "house",
                city,
                "Ce"
        );
    }

    private Vendor createVendor(String name, String street, String city) {
        var vendor = new Vendor(vendorData(name, street, city));
        em.persist(vendor);
        return vendor;
    }
}
