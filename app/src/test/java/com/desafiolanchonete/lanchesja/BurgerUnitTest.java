package com.desafiolanchonete.lanchesja;

import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.data.model.Ingredient;
import com.desafiolanchonete.lanchesja.infrastructure.Utils;
import com.desafiolanchonete.lanchesja.presenter.burgerchoice.BurgerChoiceFragment;
import com.desafiolanchonete.lanchesja.presenter.burgerchoice.BurgerChoicePresenter;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BurgerUnitTest {

    private static final Double LETTUCE = 0.40;
    private static final Double BACON = 2.0;
    private static final Double HAMBURGER = 3.0;
    private static final Double EGG = 0.8;
    private static final Double CHEESE = 1.5;
    private static final Double SESAME_BREAD = 1.0;

    private static final Double XBACON = SESAME_BREAD + BACON + HAMBURGER + CHEESE;
    private static final Double XBURGER = SESAME_BREAD + HAMBURGER + CHEESE;
    private static final Double XEGG = SESAME_BREAD + EGG + HAMBURGER + CHEESE;
    private static final Double XEGG_BACON = SESAME_BREAD + LETTUCE + EGG + BACON + HAMBURGER + CHEESE;

    private static final Double BURGER_FIRST_RULE = (XBURGER + LETTUCE) * 0.9;
    private static final Double BURGER_SECOND_RULE = XEGG + (HAMBURGER * 3);
    private static final Double BURGER_THIRD_RULE = XBACON + (CHEESE * 3);
    private static final Double BURGER_ALL_RULES = (XBURGER + (HAMBURGER * 3) + (CHEESE * 3) + LETTUCE) * 0.9;

    @Test
    public void all_rules_calculate_isCorrect() throws Exception {
        BurgerChoiceFragment burgerChoiceFragment = BurgerChoiceFragment.newInstance();
        BurgerChoicePresenter burgerChoicePresenter = new BurgerChoicePresenter(burgerChoiceFragment);

        List<Ingredient> extraIngredients = Arrays.asList(getHamburger(5),
                getCheese(5), getLettuce(1));

        assertEquals(Utils.getFormattedCurrencyDouble(BURGER_ALL_RULES),
                burgerChoicePresenter.calculateBurgerPrice(getXBurger().getIngredientList(), extraIngredients));
    }

    @Test
    public void third_rule_calculate_isCorrect() throws Exception {
        BurgerChoiceFragment burgerChoiceFragment = BurgerChoiceFragment.newInstance();
        BurgerChoicePresenter burgerChoicePresenter = new BurgerChoicePresenter(burgerChoiceFragment);

        List<Ingredient> extraIngredients = Collections.singletonList(getCheese(5));

        assertEquals(Utils.getFormattedCurrencyDouble(BURGER_THIRD_RULE),
                burgerChoicePresenter.calculateBurgerPrice(getXBacon().getIngredientList(), extraIngredients));
    }

    @Test
    public void second_rule_calculate_isCorrect() throws Exception {
        BurgerChoiceFragment burgerChoiceFragment = BurgerChoiceFragment.newInstance();
        BurgerChoicePresenter burgerChoicePresenter = new BurgerChoicePresenter(burgerChoiceFragment);

        List<Ingredient> extraIngredients = Collections.singletonList(getHamburger(5));

        assertEquals(Utils.getFormattedCurrencyDouble(BURGER_SECOND_RULE),
                burgerChoicePresenter.calculateBurgerPrice(getXEgg().getIngredientList(), extraIngredients));
    }

    @Test
    public void first_rule_calculate_isCorrect() throws Exception {
        BurgerChoiceFragment burgerChoiceFragment = BurgerChoiceFragment.newInstance();
        BurgerChoicePresenter burgerChoicePresenter = new BurgerChoicePresenter(burgerChoiceFragment);

        List<Ingredient> extraIngredients = Collections.singletonList(getLettuce(1));

        assertEquals(Utils.getFormattedCurrencyDouble(BURGER_FIRST_RULE),
                burgerChoicePresenter.calculateBurgerPrice(getXBurger().getIngredientList(), extraIngredients));
    }

    @Test
    public void xbacon_calculate_isCorrect() throws Exception {
        BurgerChoiceFragment burgerChoiceFragment = BurgerChoiceFragment.newInstance();
        BurgerChoicePresenter burgerChoicePresenter = new BurgerChoicePresenter(burgerChoiceFragment);

        assertEquals(Utils.getFormattedCurrencyDouble(XBACON),
                burgerChoicePresenter.calculateBurgerPrice(getXBacon().getIngredientList(), null));
    }

    @Test
    public void xburger_calculate_isCorrect() throws Exception {
        BurgerChoiceFragment burgerChoiceFragment = BurgerChoiceFragment.newInstance();
        BurgerChoicePresenter burgerChoicePresenter = new BurgerChoicePresenter(burgerChoiceFragment);

        assertEquals(Utils.getFormattedCurrencyDouble(XBURGER),
                burgerChoicePresenter.calculateBurgerPrice(getXBurger().getIngredientList(), null));
    }

    @Test
    public void xegg_calculate_isCorrect() throws Exception {
        BurgerChoiceFragment burgerChoiceFragment = BurgerChoiceFragment.newInstance();
        BurgerChoicePresenter burgerChoicePresenter = new BurgerChoicePresenter(burgerChoiceFragment);

        assertEquals(Utils.getFormattedCurrencyDouble(XEGG),
                burgerChoicePresenter.calculateBurgerPrice(getXEgg().getIngredientList(), null));
    }

    @Test
    public void xeggBacon_calculate_isCorrect() throws Exception {
        BurgerChoiceFragment burgerChoiceFragment = BurgerChoiceFragment.newInstance();
        BurgerChoicePresenter burgerChoicePresenter = new BurgerChoicePresenter(burgerChoiceFragment);

        assertEquals(Utils.getFormattedCurrencyDouble(XEGG_BACON),
                burgerChoicePresenter.calculateBurgerPrice(getXEggBacon().getIngredientList(), null));
    }

    private Burger getXBacon() {
        Burger burger = new Burger();
        burger.setIngredientList(Arrays.asList(
                getSesameBread(1),
                getBacon(1),
                getHamburger(1),
                getCheese(1)));

        return burger;
    }

    private Burger getXBurger() {
        Burger burger = new Burger();
        burger.setIngredientList(Arrays.asList(
                getSesameBread(1),
                getHamburger(1),
                getCheese(1)));

        return burger;
    }

    private Burger getXEgg() {
        Burger burger = new Burger();
        burger.setIngredientList(Arrays.asList(
                getSesameBread(1),
                getEgg(1),
                getHamburger(1),
                getCheese(1)));

        return burger;
    }

    private Burger getXEggBacon() {
        Burger burger = new Burger();
        burger.setIngredientList(Arrays.asList(
                getSesameBread(1),
                getLettuce(1),
                getEgg(1),
                getBacon(1),
                getHamburger(1),
                getCheese(1)));

        return burger;
    }

    private Ingredient getLettuce(int extraQuantity) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setPrice(0.40);
        ingredient.setExtraQuantity(extraQuantity);
        return ingredient;
    }

    private Ingredient getBacon(int extraQuantity) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(2);
        ingredient.setPrice(2.0);
        ingredient.setExtraQuantity(extraQuantity);
        return ingredient;
    }

    private Ingredient getHamburger(int extraQuantity) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3);
        ingredient.setPrice(3.0);
        ingredient.setExtraQuantity(extraQuantity);
        return ingredient;
    }

    private Ingredient getEgg(int extraQuantity) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(4);
        ingredient.setPrice(0.8);
        ingredient.setExtraQuantity(extraQuantity);
        return ingredient;
    }

    private Ingredient getCheese(int extraQuantity) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(5);
        ingredient.setPrice(1.5);
        ingredient.setExtraQuantity(extraQuantity);
        return ingredient;
    }

    private Ingredient getSesameBread(int extraQuantity) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(6);
        ingredient.setPrice(1.0);
        ingredient.setExtraQuantity(extraQuantity);
        return ingredient;
    }

}
