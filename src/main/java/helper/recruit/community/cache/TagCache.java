package helper.recruit.community.cache;

import helper.recruit.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();

        program.setCategoryName("Languages");
        program.setTags(Arrays.asList("Java", "Python", "CSS", "HTML", "Javascript", "PHP", "C++", "C", "Go", "Swift"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("Framework");
        framework.setTags(Arrays.asList("Spring", "Spring Boot", "Express", "Django", "Flask"));
        tagDTOS.add(framework);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("Tools");
        tool.setTags(Arrays.asList("Git", "Github", "Linux", "Ubuntu", "Vim", "Nginx", "Apache", "Eclipse", "Maven", "Tomcat", "Unix"));
        tagDTOS.add(tool);

        TagDTO db = new TagDTO();
        db.setCategoryName("Database");
        db.setTags(Arrays.asList("Mysql", "Redis", "Mongodb", "SQL", "Sqlite"));
        tagDTOS.add(db);

        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
