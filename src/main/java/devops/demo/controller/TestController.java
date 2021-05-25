package devops.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("hello/{content}")
    public String hello(@PathVariable(value = "content") String content) {
        log.debug("----------log debug----------");
        log.info("----------log info----------");
        log.warn("----------log warn----------");
        log.error("----------log error----------");
        return "返回结果：" + content;
    }

}
