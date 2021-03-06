package com.codeborne.selenide.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;

public class SelectOptionByTextOrIndex implements Command<Void> {
  @Override
  public Void execute(SelenideElement proxy, WebElementSource selectField, Object[] args) {
    if (args[0] instanceof String[]) {
      selectOptionsByTexts(selectField, (String[]) args[0]);
    }
    else {
      selectOptionsByIndexes(selectField, (int[]) args[0]);
    }
    return null;
  }

  private void selectOptionsByTexts(WebElementSource selectField, String[] texts) {
    Select select = new Select(selectField.getWebElement());
    for (String text : texts) {
      try {
        select.selectByVisibleText(text);
      } catch (NoSuchElementException e) {
        throw new ElementNotFound(selectField.getSearchCriteria() + "/option[text:" + text + ']', Condition.exist, e);
      }
    }
  }

  private void selectOptionsByIndexes(WebElementSource selectField, int[] indexes) {
    Select select = new Select(selectField.getWebElement());
    for (Integer index : indexes) {
      try {
        select.selectByIndex(index);
      } catch (NoSuchElementException e) {
        throw new ElementNotFound(selectField.getSearchCriteria() + "/option[index:" + index + ']', Condition.exist, e);
      }
    }
  }
}
