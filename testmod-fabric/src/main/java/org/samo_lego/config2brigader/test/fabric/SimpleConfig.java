package org.samo_lego.config2brigader.test.fabric;


import com.google.gson.annotations.SerializedName;
import org.samo_lego.config2brigadier.IConfig2B;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleConfig implements IConfig2B {

    // Naming comments in style `_comment_` + `field name`
    @SerializedName("// When to activate feature xyz.")
    public final String _comment_activationRange = "(default: 8.0)";
    @SerializedName("activation_range")
    public float activationRange = 8.0F;


    @SerializedName("// Whether to show config message.")
    public final String _comment_show = "(default: true)";
    @SerializedName("show_config_message.")
    public boolean show = true;

    @SerializedName("// Which message to print out")
    public final String _comment_message0 = "";
    @SerializedName("// if above option is enabled")
    public final String _comment_message1 = "(default: 'This is a config guide.')";
    @SerializedName("config_message")
    public String message = "This is a config guide.";

    @SerializedName("// A secret toggle that is not included in edit command.")
    public final String _comment_secretHiddenToggle = "(default: false)";
    public boolean secretHiddenToggle = false;

    public NestedValues nested = new NestedValues();

    @Override
    public void save() {
        System.out.println(this);
    }

    @Override
    public boolean isDescription(Field field) {
        return field.getName().startsWith("_comment_");
    }

    @Override
    public boolean shouldExclude(Field field) {
        return this.isDescription(field) || field.getName().equals("secretHiddenToggle");
    }

    public static class NestedValues {
        public String messageNested = "This is a another message.";
    }

    public List<String> randomQuestions = new ArrayList<>(Arrays.asList(
            "Why no forge port?",
            "When quilt?",
            "Tiny potato or tiny pumpkin?",
            "What is minecraft?" // How dare you
    ));

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Field f : this.getClass().getFields()) {
            try {
                result.append(f.getName()).append(": ").append(f.get(this)).append("\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
