package jpabook.sbjpashop.api;

import jpabook.sbjpashop.domain.Member;
import jpabook.sbjpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 이렇게 하면 xx
     * api 스펙이 변하면 안되기 때문에 api 스펙에 맞춰서 별도의 dto 를 생성해서 받는게 좋다!
     * 엔티티 절대 파라미터로 받지말기
     * */
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    /**
     * 요론식으로 dto 생성해서 받기! 좋은 예!!
     * */
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemeberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    static  class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
