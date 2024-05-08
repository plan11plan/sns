//package com.example.sns.unit.user.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
//
//import com.example.sns.common.exception.CertificationCodeNotMatchedException;
//import com.example.sns.common.exception.ResourceNotFoundException;
//import com.example.sns.core.user.domain.entity.Email;
//import com.example.sns.core.user.domain.entity.Nickname;
//import com.example.sns.core.user.domain.entity.Sex;
//import com.example.sns.core.user.domain.entity.UserStatus;
//import com.example.sns.core.user.domain.entity.root.User;
//import com.example.sns.core.user.domain.request.UserCreate;
//import com.example.sns.core.user.domain.request.UserUpdate;
//import com.example.sns.core.user.service.CertificationService;
//import com.example.sns.mock.FakeMailSender;
//import com.example.sns.mock.FakeUserRepository;
//import com.example.sns.mock.TestClockHolder;
//import com.example.sns.mock.TestUuidHolder;
//import java.time.LocalDate;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class UserServiceTest {
//
//    private UserCommandService userCommandService;
//    private UserReadService userReadService;
//
//    @BeforeEach
//    void init() {
//        FakeMailSender fakeMailSender = new FakeMailSender();
//        FakeUserRepository fakeUserRepository = new FakeUserRepository();
//        //
//        this.userCommandService = UserServiceImpl.builder()
//            .uuidHolder(new TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"))
//            .clockHolder(new TestClockHolder(1678530673958L))
//            .userRepository(fakeUserRepository)
//            .certificationService(new CertificationService(fakeMailSender))
//            .build();
//        this.userReadService = UserReadServiceImpl.builder()
//                .userRepository(fakeUserRepository).build();
//        // input1
//        fakeUserRepository.save(User.builder()
//            .id(1L)
//            .email(new Email("email@gmail.com"))
//                        .userInfo(new UserInfo(new Name("name"), new Nickname("nickname"), Sex.M, LocalDate.now()))
//            .address(new Address("address"))
//            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
//            .userStatus(UserStatus.ACTIVE)
//            .lastLoginAt(0L)
//            .build());
//        // input2
//        fakeUserRepository.save(User.builder()
//                .id(1L)
//                .email(new Email("email2@gmail.com"))
//                .userInfo(new UserInfo(new Name("name2"), new Nickname("nickname2"), Sex.W, LocalDate.now()))
//                .address(new Address("address2"))
//                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
//                .userStatus(UserStatus.ACTIVE)
//                .lastLoginAt(0L)
//                .build());
//    }
//
//    @Test
//    void getByEmail은_ACTIVE_상태인_유저를_찾아올_수_있다() {
//        // given
//        String email = "kok202@naver.com";
//
//        // when
//        User result = userReadService.getByEmail(email);
//
//        // then
//        assertThat(result.getNicknameValue()).isEqualTo("kok202");
//    }
//
//    @Test
//    void getByEmail은_PENDING_상태인_유저는_찾아올_수_없다() {
//        // given
//        String email = "kok303@naver.com";
//
//        // when
//        // then
//        assertThatThrownBy(() -> {
//            userReadService.getByEmail(email);
//        }).isInstanceOf(ResourceNotFoundException.class);
//    }
//
//    @Test
//    void getById는_ACTIVE_상태인_유저를_찾아올_수_있다() {
//        // given
//        // when
//        User result = userReadService.getById(1);
//
//        // then
//        assertThat(result.getNicknameValue()).isEqualTo("kok202");
//    }
//
//    @Test
//    void getById는_PENDING_상태인_유저는_찾아올_수_없다() {
//        // given
//        // when
//        // then
//        assertThatThrownBy(() -> {
//            userReadService.getById(2);
//        }).isInstanceOf(ResourceNotFoundException.class);
//    }
//
//    @Test
//    void userCreate_를_이용하여_유저를_생성할_수_있다() {
//        // given
//        UserCreate userCreate = UserCreate.builder()
//            .email("kok202@kakao.com")
//            .address("Gyeongi")
//            .nickname("kok202-k")
//            .build();
//
//        // when
//        User result = userCommandService.create(userCreate);
//
//        // then
//        assertThat(result.getId()).isNotNull();
//        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING);
//        assertThat(result.getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");
//    }
//
//    @Test
//    void userUpdateDto_를_이용하여_유저를_수정할_수_있다() {
//        // given
//        UserUpdate userUpdate = UserUpdate.builder()
//            .address("Incheon")
////            .nickname("kok202-n")
//            .build();
//
//        // when
//        userCommandService.update(1, userUpdate);
//
//        // then
//        User user = userReadService.getById(1);
//        assertThat(user.getId()).isNotNull();
//        assertThat(user.getAddress()).isEqualTo("Incheon");
//        assertThat(user.getNicknameValue()).isEqualTo("kok202-n");
//    }
//
//    @Test
//    void user를_로그인_시키면_마지막_로그인_시간이_변경된다() {
//        // given
//        // when
//        userCommandService.login(1);
//
//        // then
//        User user = userReadService.getById(1);
//        assertThat(user.getLastLoginAt()).isEqualTo(1678530673958L);
//    }
//
//    @Test
//    void PENDING_상태의_사용자는_인증_코드로_ACTIVE_시킬_수_있다() {
//        // given
//        // when
//        userCommandService.verifyEmail(2, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");
//
//        // then
//        User user = userReadService.getById(2);
//        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
//    }
//
//    @Test
//    void PENDING_상태의_사용자는_잘못된_인증_코드를_받으면_에러를_던진다() {
//        // given
//        // when
//        // then
//        assertThatThrownBy(() -> {
//            userCommandService.verifyEmail(2, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaac");
//        }).isInstanceOf(CertificationCodeNotMatchedException.class);
//    }
//
//}
