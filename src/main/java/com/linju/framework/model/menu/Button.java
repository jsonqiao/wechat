package com.linju.framework.model.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单基础字段
 *
 * @author jsonqiao
 *
 * @date 2015/7/3
 */
public class Button {

    // 菜单类型
    private String type;

    // 菜单名称
    private String name;

    // 子菜单
    private List<Button> subButtons = new ArrayList<>();

    public final void addSubButton(Button button) {
        subButtons.add(button);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Button> getSubButtons() {
        return subButtons;
    }

    public void setSubButtons(List<Button> subButtons) {
        this.subButtons = subButtons;
    }
}
