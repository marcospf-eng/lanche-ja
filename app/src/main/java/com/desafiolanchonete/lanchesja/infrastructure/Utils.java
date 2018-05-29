package com.desafiolanchonete.lanchesja.infrastructure;

import com.desafiolanchonete.lanchesja.data.model.Ingredient;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public abstract class Utils {

    public static String getFormattedCurrencyDouble(final Double value) {
        Locale locale = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(locale).format(value);
    }

    public static String getFormattedIngredientList(final List<Ingredient> ingredientList) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (ingredientList != null && !ingredientList.isEmpty()) {
            for (Ingredient ingredient : ingredientList) {
                if (ingredient != null) {
                    stringBuilder.append(ingredient.getName());
                    if (!ingredientList.get(ingredientList.size() - 1).equals(ingredient)) {
                        if (ingredientList.size() >= 2 && ingredientList.get(ingredientList.size() - 2).equals(ingredient)) {
                            stringBuilder.append(" e ");
                        } else {
                            stringBuilder.append(", ");
                        }
                    } else {
                        stringBuilder.append(".");
                    }
                }
            }
        } else {
            stringBuilder.append("-");
        }

        return stringBuilder.toString();
    }

}
