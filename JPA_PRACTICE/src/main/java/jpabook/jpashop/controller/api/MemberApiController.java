package jpabook.jpashop.controller.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
   private final MemberService memberService;

   @PostMapping("/api/v1/members")
   public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
       Long id = memberService.join(member);
       return new CreateMemberResponse(id);
   }

   @PostMapping("/api/v2/members")
   public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
       Member member = request.toEntity();
       Long id = memberService.join(member);

       return new CreateMemberResponse(id);
   }

   @Data
   static class CreateMemberRequest {
       private String name;

       public Member toEntity() {
           Member member = new Member();
           member.setName(this.name);

           return member;
       }
   }

   @Data
   @AllArgsConstructor
   static class CreateMemberResponse {
       private Long id;
   }


}
