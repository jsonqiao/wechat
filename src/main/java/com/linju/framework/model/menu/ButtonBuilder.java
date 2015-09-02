package com.linju.framework.model.menu;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * button创建类
 *
 * @author jsonqiao
 *
 * @date 2015/7/3
 */
public class ButtonBuilder {

    public static final int DEFUALT_MAX_SIZE = 3;

    private int maxSize = DEFUALT_MAX_SIZE;

    private List<Button> button = new ArrayList<>();

    public List<Button> getButton() {
        return button;
    }

    public void add(Button button) {
        if (this.button.size() == maxSize) {
            throw new UnsupportedOperationException("");
        }
        this.button.add(button);
    }

    public static void main(String[] args) {
        ButtonBuilder buttonBuilder = new ButtonBuilder();
        EventButton b = new EventButton();
        b.setName("demo");
        b.setType("click");
        b.setKey("0001");
        buttonBuilder.add(b);

        System.out.println(JSONObject.toJSON(buttonBuilder));
    }
}
