//package com.example.sns.mock;
//
//import com.example.sns.common.service.port.ClockHolder;
//import com.example.sns.common.service.port.UuidHolder;
//import com.example.sns.core.post.service.port.PostRepository;
//import com.example.sns.core.user.service.AuthenticationService;
//import com.example.sns.core.user.service.CertificationService;
//import com.example.sns.core.user.service.UserCreateService;
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
//    public final PostRepository postRepository;
//    /// UserService
//    private final UserUpdateService userUpdateService;
//    private final UserCreateService userCreateService;
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
//        this.postRepository = new FakePostRepository();
//        this.postCommandService = PostCommandServiceImpl.builder()
//                .postRepository(this.postRepository)
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
//        this.userCreateService = UserCreateService.builder()
//                .userRepository(this.userRepository)
//                .certificationService(this.certificationService)
//                .uuidHolder(uuidHolder)
//                .build();
//
//    }
//}
