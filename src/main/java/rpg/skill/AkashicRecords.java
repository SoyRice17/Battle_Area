package rpg.skill;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import rpg.skill.skills.*;

/**
 * 모든 스킬 레코드를 관리하는 클래스입니다.
 * 싱글톤 패턴으로 구현되어 있습니다.
 * 
 * <p>
 * 주요 기능:
 * <ul>
 *  <li>스킬 등록</li>
 *  <li>스킬 레벨별 등록</li>
 *  <li>스킬 조회</li>
 *  <li>스킬 레벨별 조회</li>
    *  <li>스킬 존재 여부 확인</li>
 * </ul>
 * 
 * <p>
 * 사용 예시:
 * <pre>
 *     AkashicRecords akashicRecords = AkashicRecords.getInstance();
 * </pre>
 */
public class AkashicRecords {
    private static AkashicRecords instance;
    private Map<String, Skill> records = new HashMap<>();
    private Map<Integer, List<Skill>> recordsByLevel = new HashMap<>();

    private AkashicRecords() {
        // 생성자
        registerSkill(new BodySlam());
        registerSkill(new Power_Attack());
        registerSkill(new PoisonTouch());

        registerSkillByLevel(new BodySlam());
        registerSkillByLevel(new Power_Attack());
        registerSkillByLevel(new PoisonTouch());
    }
    
    public static AkashicRecords getInstance() {
        if (instance == null) {
            instance = new AkashicRecords();
        }
        return instance;
    }
    
    private void registerSkill(Skill skill) {
        records.put(skill.getName(), skill);
    }

    private void registerSkillByLevel(Skill skill) {
        recordsByLevel.putIfAbsent(skill.getCanUseLevel(), new ArrayList<>());
        recordsByLevel.get(skill.getCanUseLevel()).add(skill);
    }

    public Skill getSkill(String name) {
        return records.get(name);
    }

    public List<Skill> getSkillByLevel(int level) {
        List<Skill> result = new ArrayList<>();
        for (List<Skill> skills : recordsByLevel.values()) {
            for (Skill skill : skills) {
                if (skill.getCanUseLevel() <= level) {
                    result.add(skill);
                }
            }
        }
        return result;
    }

    public boolean isSkillExist(String name) {
        return records.containsKey(name);
    }
    public boolean isSkillExist(Skill skill) {
        return records.containsKey(skill.getName());
    }
}
