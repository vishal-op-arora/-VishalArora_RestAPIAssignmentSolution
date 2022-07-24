package io.vishal.employee.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.vishal.employee.model.Role;
import io.vishal.employee.model.Employee;
import io.vishal.employee.model.User;
import io.vishal.employee.repository.RoleRepository;
import io.vishal.employee.repository.EmployeeRepository;
import io.vishal.employee.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BootStrapApplicationData implements ApplicationListener<ApplicationReadyEvent> {

	private final PasswordEncoder passwordEncoder;
	
	private final RoleRepository roleRepository;
	
	private final UserRepository userRepository;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		
		
		Role userRole=new Role();
		userRole.setRoleName("ROLE_USER");
		
		Role adminRole=new Role();
		adminRole.setRoleName("ROLE_ADMIN");

		User user=new User();
		user.setUserName("user");
		user.setPassword(this.passwordEncoder.encode("user"));
		
		User admin=new User();
		admin.setUserName("admin");
		admin.setPassword(this.passwordEncoder.encode("admin"));
				
		admin.addRole(adminRole);
		admin.addRole(userRole);
		
		user.addRole(userRole);
		
		userRepository.save(user);
		userRepository.save(admin);
		
		roleRepository.save(userRole);
		roleRepository.save(adminRole);
		
	}

}
