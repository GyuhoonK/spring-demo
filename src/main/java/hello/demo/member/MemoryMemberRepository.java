package hello.demo.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 저장소를 위한 Map
    // 동시 접근을 위해 concurrent hash map을 사용해야함
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long MemberId) {
        return store.get(MemberId);
    }
}
