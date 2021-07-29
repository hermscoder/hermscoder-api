package com.herms.hermscoder.service;

import com.herms.hermscoder.model.dto.*;
import com.herms.hermscoder.model.entity.Experience;
import com.herms.hermscoder.model.entity.Profile;
import com.herms.hermscoder.model.entity.User;
import com.herms.hermscoder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.sql.Date;
import java.util.Collections;

@Service
public class DBService {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileServiceImpl profileService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instantiateTestDatabase() {

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setBirthDate("05/01/1996");
        profileDTO.setDescription("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.");
        profileDTO.setGivenName("Emerson");
        profileDTO.setFamilyName("Ribeiro Junior");
        profileDTO.setJobTitle("Web Developer");

        UserRegistration userRegistration = new UserRegistration("emersonrjr03@gmail.com", "emersonrjr03", profileDTO);

        authService.signUp(userRegistration);

        User user = userService.findByEmail(userRegistration.getEmail(), Collections.singleton(Profile.class)).get();

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setTitle("TODO List");
//        projectDTO.setThumbnail();
        projectDTO.setDescription("The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\"");
        projectDTO.setUrlToProject("google.com");
        projectDTO.setProfileId(user.getProfile().getId());
        profileService.addProject(projectDTO);

        projectDTO = new ProjectDTO();
        projectDTO.setTitle("Portfolio website");
//        projectDTO.setThumbnail();
        projectDTO.setDescription("Curabitur mattis ultricies tincidunt. In sit amet nulla accumsan, condimentum lectus vitae, blandit felis. ");
        projectDTO.setUrlToProject("hermscoder.github.io");
        projectDTO.setProfileId(user.getProfile().getId());
        profileService.addProject(projectDTO);

        ExperienceDTO experienceDTO = new ExperienceDTO();
        experienceDTO.setProfileId(user.getProfile().getId());
        experienceDTO.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vitae iaculis erat, lacinia pellentesque diam. Maecenas eu lacus metus. Integer interdum ante et ex viverra sagittis");
        experienceDTO.setJobTitle("Delphi developer");
        experienceDTO.setStartDate("08/04/2014");
        experienceDTO.setEndDate("08/04/2015");
        experienceDTO.setLocal("Uberlândia, Brasil");
        experienceDTO.setCompanyName("TW Tecnologia");
        profileService.addExperience(experienceDTO);


        experienceDTO = new ExperienceDTO();
        experienceDTO.setProfileId(user.getProfile().getId());
        experienceDTO.setDescription("Nam eget euismod dui. In mattis urna eget congue tincidunt. Maecenas venenatis arcu quis ligula imperdiet, ac scelerisque erat tristique. Praesent nec hendrerit sapien. Sed elementum ultrices risus vel maximus. Aenean feugiat mauris sed ullamcorper facilisis. Quisque pulvinar maximus mauris, vel lobortis lacus suscipit quis. Curabitur consequat, sem vitae gravida luctus, erat felis vestibulum est, sed sollicitudin elit neque sit amet purus. ");
        experienceDTO.setJobTitle("Java developer");
        experienceDTO.setStartDate("13/04/2015");
        experienceDTO.setEndDate("20/08/2016");
        experienceDTO.setLocal("Uberlândia, Brasil");
        experienceDTO.setCompanyName("Sankhya");
        profileService.addExperience(experienceDTO);
    }
}
