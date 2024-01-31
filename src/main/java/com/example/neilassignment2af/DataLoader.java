package com.example.neilassignment2af;

import com.example.neilassignment2af.entities.Property;
import com.example.neilassignment2af.entities.Tenant;
import com.example.neilassignment2af.entities.MyUser;
import com.example.neilassignment2af.repositories.PropertyRepository;
import com.example.neilassignment2af.repositories.TenantRepository;
import com.example.neilassignment2af.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final PropertyRepository propertyRepository;
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;

    public DataLoader(PropertyRepository propertyRepository, TenantRepository tenantRepository, UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Property property1 = propertyRepository.save(new Property("123 Main St", "G56 B789", 6, 3600));
        Property property2 = propertyRepository.save(new Property("456 Side St", "C12 G098", 4,3200));
        Property property3 = propertyRepository.save(new Property("789 New St", "A12 L098", 4, 2250));
        Property property4 = propertyRepository.save(new Property("1 Back St", "D12 F987", 5, 3500));
        Property property5 = propertyRepository.save(new Property("2 Old St", "A19 B129", 3, 2000));
        propertyRepository.save(new Property("3 Old St", "A11 B169", 1, 1500));
        propertyRepository.save(new Property("9 Old St", "A11 B154", 2, 1800));

        tenantRepository.save(new Tenant("John Murphy", "john.murphy@gmail.com", "083456789",property1));
        tenantRepository.save(new Tenant("Alex Smith", "jane.smith@gmail.com", "087654321",property1));
        tenantRepository.save(new Tenant("Ray Brennan", "ray.brennan@gmail.com", "085676443",property1));
        tenantRepository.save(new Tenant("Bob Williams", "bob.williams@gmail.com", "087234521",property1));
        tenantRepository.save(new Tenant("Eva Braun", "eva.braun@gmail.com", "087156845",property1));
        tenantRepository.save(new Tenant("Neil O Sullivan", "neil.osullivan@gmail.com", "083454789",property2));
        tenantRepository.save(new Tenant("Ray Wisely", "ray.wisely@gmail.com", "087654341",property2));
        tenantRepository.save(new Tenant("Bon Jovi", "bon.jovi@gmail.com", "085616443",property2));
        tenantRepository.save(new Tenant("Tom Williams", "tom.williams@gmail.com", "087134521",property2));
        tenantRepository.save(new Tenant("Evan Henry", "evan.henry@gmail.com", "087156847",property3));
        tenantRepository.save(new Tenant("John Jones", "john.jones@gmail.com", "083456788",property3));
        tenantRepository.save(new Tenant("Alex Arnold", "alex.arnold@gmail.com", "087654322",property3));
        tenantRepository.save(new Tenant("Curtis Jones", "curtis.jones@gmail.com", "085576443",property4));
        tenantRepository.save(new Tenant("Neco Williams", "neco.williams@gmail.com", "087211521",property4));
        tenantRepository.save(new Tenant("Mo Salah", "mo.salah@gmail.com", "081156845",property4));
        tenantRepository.save(new Tenant("Darwin Nunez", "darwin.nunez@gmail.com", "083456999",property4));
        tenantRepository.save(new Tenant("Luis Diaz", "luis.diaz@gmail.com", "087651111",property4));
        tenantRepository.save(new Tenant("Ali Son", "ali.son@gmail.com", "085676333",property5));
        tenantRepository.save(new Tenant( "Jurgen Klopp", "jurgen.klopp@gmail.com", "084134521",property5));
        tenantRepository.save(new Tenant("Ally Sanders", "ally.sanders@gmail.com", "0871112845", property5));

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        userRepository.save((new MyUser("staff@gmail.com", passwordEncoder.encode("Secret123"), false, "OFFICE_STAFF", "083456789", "P1456897")));
        userRepository.save((new MyUser("manager@gmail.com", passwordEncoder.encode("Secret123"), false, "MANAGER", "083454789", "P2569874")));
        userRepository.save((new MyUser("locked@gmail.com", passwordEncoder.encode("Secret123"), true, "OFFICE_STAFF", "087654341", "F1458975")));
    }
}
