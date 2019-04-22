import { AppPage } from './app.po';
import { browser, element, by } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Buyers and sellers BOTH win!');
  });
});

describe('Login Page', () => {
  it('should set user data in local storage', () => {
    browser.get('http://localhost:4200/onboard');
    browser.executeScript('window.localStorage.clear();');
    const username = 'oneworkingexample';
    element(by.id('usernameInput')).clear();
    element(by.id('usernameInput')).sendKeys(username);
    const userButton = element(by.id('loginButton'));
    userButton.click();
    browser.sleep(1000);

    const value = browser.executeScript('return window.localStorage.getItem(\'userId\');');
    expect(value).toBe('29');
  });
});

describe('Registration page', () => {
  it('should set correct user id in local storage', () => {
    browser.get('http://localhost:4200/register');
    browser.executeScript('window.localStorage.clear();');
    element(by.id('buyerRadioButton')).click();

    const date = new Date();
    const username = 'testusername' + date.getTime();
    const usernameInput = element(by.id('usernameInput'));
    usernameInput.clear();
    usernameInput.sendKeys(username);
    element(by.id('registerButton')).click();
    browser.sleep(1000);

    const roles = browser.executeScript('return window.localStorage.getItem(\'roles\');');
    expect(roles).toBeTruthy();
    expect(roles).toContain('user_Id');
    expect(roles).toContain('role');
  });

  it('should register a buyer with the correct role', () => {
    browser.get('http://localhost:4200/register');
    browser.executeScript('window.localStorage.clear();');
    element(by.id('buyerRadioButton')).click();

    const date = new Date();
    const username = 'testbuyer' + date.getTime();
    const usernameInput = element(by.id('usernameInput'));
    usernameInput.clear();
    usernameInput.sendKeys(username);
    element(by.id('registerButton')).click();
    browser.sleep(1000);

    const roles = browser.executeScript('return window.localStorage.getItem(\'roles\');');
    expect(roles).toBeTruthy();
    expect(roles).toContain('user_Id');
    expect(roles).toContain('role');
    expect(roles).toContain('Buyer');
  });

  it('should register a seller with the correct role', () => {
    browser.get('http://localhost:4200/register');
    browser.executeScript('window.localStorage.clear();');
    element(by.id('sellerRadioButton')).click();

    const date = new Date();
    const username = 'testseller' + date.getTime();
    const usernameInput = element(by.id('usernameInput'));
    usernameInput.clear();
    usernameInput.sendKeys(username);
    element(by.id('registerButton')).click();
    browser.sleep(1000);

    const roles = browser.executeScript('return window.localStorage.getItem(\'roles\');');
    expect(roles).toBeTruthy();
    expect(roles).toContain('user_Id');
    expect(roles).toContain('role');
    expect(roles).toContain('Seller');
  });

  it('should register an admin with the correct role', () => {
    browser.get('http://localhost:4200/register');
    browser.executeScript('window.localStorage.clear();');
    element(by.id('bothRadioButton')).click();

    const date = new Date();
    const username = 'testAdmin' + date.getTime();
    const usernameInput = element(by.id('usernameInput'));
    usernameInput.clear();
    usernameInput.sendKeys(username);
    element(by.id('registerButton')).click();
    browser.sleep(1000);

    const roles = browser.executeScript('return window.localStorage.getItem(\'roles\');');
    expect(roles).toBeTruthy();
    expect(roles).toContain('user_Id');
    expect(roles).toContain('role');
    expect(roles).toContain('Admin');
  });
});
