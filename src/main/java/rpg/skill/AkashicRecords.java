package rpg.skill;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import rpg.skill.skills.*;

public class AkashicRecords {
    private static AkashicRecords instance;
    private Map<String, Skill> records = new HashMap<>();
    private Map<Integer, List<Skill>> recordsByLevel = new HashMap<>();

    private AkashicRecords() {
        // 생성자
        registerSkill(new BodySlam());
        registerSkill(new Power_Attack());

        registerSkillByLevel(new BodySlam());
        registerSkillByLevel(new Power_Attack());
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
