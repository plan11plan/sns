//package com.example.sns.mock;
//
//import com.example.sns.common.service.port.ClockHolder;
//import com.example.sns.common.service.port.UuidHolder;
//import com.example.sns.core.post.service.port.PostWriteRepository;
//import com.example.sns.core.user.service.AuthenticationService;
//import com.example.sns.core.user.service.CertificationService;
//import com.example.sns.core.user.service.UserSignupService;
//import com.example.sns.core.user.service.UserReadService;
//import com.example.sns.core.user.service.UserUpdateService;
//import com.example.sns.core.user.service.port.MailSender;
//import com.example.sns.core.user.service.port.UserRepository;
//import lombok.Builder;
//
//public class TestContainer {
//
//    public final MailSender mailSender;
//    public final UserRepository userRepository;
//    public final PostWriteRepository postWriteRepository;
//    /// UserService
//    private final UserUpdateService userUpdateService;
//    private final UserSignupService userCreateService;
//    private final AuthenticationService authenticationService;
//    private final UserReadService userReadService;
//    /// PostService
//    public final PostCommandService postCommandService;
//    public final CertificationService certificationService;
//
//
//    @Builder
//    public TestContainer(ClockHolder clockHolder, UuidHolder uuidHolder) {
//        this.mailSender = new FakeMailSender();
//        this.userRepository = new FakeUserRepository();
//        this.postWriteRepository = new FakePostRepository();
//        this.postCommandService = PostCommandServiceImpl.builder()
//                .postWriteRepository(this.postWriteRepository)
//                .userRepository(this.userRepository)
//                .clockHolder(clockHolder)
//                .build();
//        this.certificationService = new CertificationService(this.mailSender);
//
//        ///
//        this.authenticationService = AuthenticationService.builder()
//                .userRepository(this.userRepository)
//                .clockHolder(clockHolder)
//                .build();
//        this.userReadService = UserReadService.builder().userRepository(this.userRepository).build();
//        this.userUpdateService = UserUpdateService.builder().userRepository(this.userRepository).build();
//        this.userCreateService = UserSignupService.builder()
//                .userRepository(this.userRepository)
//                .certificationService(this.certificationService)
//                .uuidHolder(uuidHolder)
//                .build();
//
//    }
//}
