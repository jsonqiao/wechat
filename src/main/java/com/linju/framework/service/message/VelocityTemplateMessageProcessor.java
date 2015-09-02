package com.linju.framework.service.message;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.Map;

/**
 * 利用Velocity生成模版
 *
 * @author jsonqiao
 *
 * @date 2015/6/25
 */
public class VelocityTemplateMessageProcessor {

    private VelocityEngine velocityEngine;

    // 模版文件路径
    private String templatePath;

    private String encoding;

    protected String build(Map model) {
        return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath, encoding, model);
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
