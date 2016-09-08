package frame.ui.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import frame.ui.assertion.Assertion;
import frame.ui.element.BaseElement;
import frame.ui.element.Clicker;
import frame.ui.element.FindElement;
import frame.ui.element.SwitchWindow;
import frame.ui.element.UIWindow;

/**
 * 浏览器操作类
 */
public class Browser extends BaseElement{

    private static final Logger logger = LogManager.getLogger(Browser.class);
    protected UIWindow uiwindow;
	protected SwitchWindow switchwindow;
	protected Clicker clicker;
	protected FindElement findElement;
	
	public Browser(RemoteWebDriver rw) {
		super(rw);
		this.uiwindow = new UIWindow(rw);
		this.switchwindow = new SwitchWindow(rw);
		this.clicker = new Clicker(rw);
		this.findElement= new FindElement(rw);
	}

	

//	public WebElement wait(WebElement element){
//		return browser.wait(element);
//	}

	
//	public void setSize (int height,int width){
//		uiwindow.setSize(height,width);
//	}
	/**
	 * 加载新的页面
	 *
	 * @param url
	 */
	public void get(String url) {
		
		uiwindow.get(url);
	}

	/**
	 * 退出当前driver
	 */
	public void quit() {
		uiwindow.quit();
	}

	/**
	 * 模拟键盘动作F5刷新当前页面
	 */
	public void refresh() {
		uiwindow.refresh();
	}
	/**
	 * 添加cookie
	 */
	

	/***
	 * 查找当前页面指定文本
	 * @param text
	 */
	public Boolean searchText(String text){
		return checkIfExists("//*[contains(text(),'"+text+"')]");
	}

	/**
	 * 验证当前页面定位器能否定位到元素
	 * @param locator 页面元素定位器
	 * @return 在预定时间内定位到就返回true, 没有定位到则返回false.
	 */
	public boolean checkIfExists(String locator) {
		WebElement ele = findElement.searchUntilPresent(locator,elementTimeout);
		if (ele == null) {
			Assertion.info("元素没有找到 locator=" + locator);
			//logger.error("元素没有找到 locator=" + locator);
			return false;
		} else {
			//logger.info("元素找到 locator=" + locator);
			Assertion.info("元素没有找到 locator=" + locator);
			return true;
		}
	}

	/**
	 * 切换焦点到指定的窗口,参数为窗口生成的次序,如1是最初打开的窗口 注意执行切回原来页面的步骤时，需close当前子页面
	 * 再调用switchWindow 切回主页
	 * @param number 当前页面的索引值，默认为1 新开的页面既为2 类推
	 */
	public void switchWindow(int number) {
		switchwindow.switchWindow(number);
	}

	/**
	 * 获取当前页面的 handle
	 * @return
	 */
	public String getWindowHandle() {
		return switchwindow.getWindowHandle();
	}

	public void switchWindow(String windowHandle) {
		switchwindow.switchWindow(windowHandle);
	}

	/**
	 * 关闭当前浏览器窗口
	 */
	public void close() {
		uiwindow.close();
	}

	/**
	 * 返回上一个访问的页面
	 */
	public void goBack() {
		uiwindow.back();
	}

	/**
	 * 进到后一个页面，浏览器的前进功能
	 */
	public void goForward() {
		uiwindow.forward();
	}

	/**
	 * 鼠标左键单击一个页面元素
	 * @param locator 定位器
	 */
	public void click(String locator) {
		clicker.clickByLocator(locator);
	}

	/***
	 * 点击给定的目标element控件元素
	 * @param element
	 */
	public void click(WebElement element){
		element.click();
	}


	/**
	 * 鼠标左键单击一个页面元素
	 * @param text 文本内容
	 */
//	public void clickOnText(String text) {
//		Clicker.clickOnText(text);
//	}
//
//
//	/**
//	 * 鼠标左键单击一个按钮
//	 * @param buttonName 按钮名称
//	 */
//	public void clickOnButton(String buttonName) {
//		Clicker.clickOnButton(buttonName);
//	}
//
//
//	/***
//	 * 点击目标元素
//	 * @param locator
//	 * @param timeout
//	 */
//	public void click(String locator, int timeout) {
//		Clicker.click(locator, timeout);
//	}
//
//	/**
//	 * 获取定位元素的标签名称
//	 *
//	 * @param locator 元素定位器
//	 * @return 返回元素标签名
//	 */
//	public String getTagName(String locator) {
//		return AttrFunc.getTagName(locator, timeout);
//	}
//
//
//	/**
//	 * 获取定位元素的标签名称
//	 *
//	 * @param locator 元素定位器
//	 * @param timeout 超时时间,单位秒.
//	 * @return 返回元素标签名
//	 */
//	public String getTagName(String locator, int timeout) {
//		return AttrFunc.getTagName(locator, timeout);
//	}
//
//
//	/***
//	 * 直接输入文本值(不通过element)
//	 * @param value
//	 */
//	public void type(String value){
//		driver.switchTo().alert().sendKeys(value + Keys.ENTER);
//	}
//	
//	
//	/**
//	 * 对一个元素模拟键盘输入,清掉元素原来的值,填入新的值.如在规定时间内没有找到定位器指定的元素则停止当前用例.
//	 * @param locator 元素定位器
//	 * @param value   需要对元素输入的值
//	 */
//	public void type(String locator, String value) {
//		ElementOperation.type(locator, value,timeout);
//	}
//
	/**
	 * 不对原有的字符进行清除，只输入值
	 * @param locator
	 * @param value
	 */
    public void sendKeys(String locator, String value) {
    	WebElement ele = findElement.searchUntilPresent(locator,elementTimeout);
    	if(null == ele){
    		Assertion.error("没有找到元素locator="+locator);
    	}
    	ele.sendKeys(value);
    	Assertion.info("输入"+value+"至localtor="+locator+"成功");
    }
//
//	/**
//	 * 对一个元素模拟键盘输入,清掉元素原来的值,填入新的值.如在规定时间内没有找到定位器指定的元素则停止当前用例.
//	 * @param locator 元素定位器
//	 * @param value   需要对元素输入的值
//	 * @param timeout 超时设置,单位秒.
//	 */
//	public void type(String locator, String value,int timeout) {
//		ElementOperation.type(locator, value, timeout);
//	}
//
//
//	/**
//	 * 获取定位元素的页面可见值,包括子元素,会自动去除前后留白.超时时间根据配置文件中的 #超时时间 单位是秒 timeout 来定义
//	 * @param locator 元素定位器
//	 * @return 返回页面元素的值
//	 */
//	public String getText(String locator) {
//		return AttrFunc.getText(locator,timeout);
//	}
//	
//	/**
//	 * 获取定位元素的页面可见值,包括子元素,会自动去除前后留白.超时时间根据配置文件中的 #超时时间 单位是秒 timeout 来定义
//	 * @param element 元素定位器
//	 * @return 返回页面元素的值
//	 */
//	public String getText(WebElement element) {
//		return AttrFunc.getText(element,timeout);
//	}
//
//
//	/**
//	 * 获取定位元素的页面可见值,包括子元素,会自动去除前后留白.
//	 * @param locator 元素定位器
//	 * @param timeout 超时设置,单位秒.可自行设置超时时间
//	 * @return 定位元素的可见文本, 包含子元素.
//	 */
//	public String getText(String locator, int timeout) {
//		return AttrFunc.getText(locator, timeout);
//	}
//
//
//	/**
//	 * 获取一个给定元素的属性值 超时时间根据配置文件中的 #超时时间 单位是秒 timeout 来定义
//	 * @param locator   元素定位器
//	 * @param attribute 元素属性
//	 * @return 返回该元素属性内容
//	 */
//	public String getAttribute(String locator, String attribute) {
//		return AttrFunc.getAttribute(locator, attribute,timeout);
//	}
//
//
//
//	/**
//	 * 获取一个给定元素的属性值
//	 * @param locator   元素定位器
//	 * @param attribute 要获取的属性
//	 * @param timeout   超时设置,单位秒. 可自行设置超时时间
//	 * @return 返回元素属性的字符串
//	 */
//	public String getAttribute(WebElement element,String attribute){
//		return AttrFunc.getAttribute(element, attribute);
//	}
//
//	/**
//	 * 获取给定元素的css属性值
//	 * @param locator 元素定位器
//	 * @param propertyName css属性
//	 * @return 返回css属性值
//	 */
//	public String getCssValue(String locator,String propertyName){
//		return AttrFunc.getCssValue(locator, propertyName, timeout);
//	}
//
//	/**
//	 * 获取给定元素的css和颜色有关的属性
//	 * @param locator 元素定位器
//	 * @param propertyName css属性
//	 * @return 返回css属性值
//	 */
//	public String getCssColor(String locator,String propertyName){
//		return AttrFunc.getCssColor(locator, propertyName, timeout);
//	}
//	/**
//	 * 选择下拉列表,有些元素看着像下拉列表其实是js控制的div,使用前最好先看一下元素的标签是否为select.
//	 * @param selectLocator 页面元素定位器
//	 * @param optionLocator 指定要选择的元素
//	 */
//	public void select(String selectLocator, String optionLocator) {
//		Selector.selectByVisibleText(selectLocator, optionLocator);
//	}
//
//	/**
//	 * 选择下拉列表,传入select的是int
//	 * @param selectLocator 页面元素定位器
//	 * @param index         指定要选择的元素
//	 */
//	public void select(String selectLocator, int index) {
//		Selector.selectByIndex(selectLocator, index);
//	}
//
//
//	/**
//	 * 选择下拉列表,传入select的是int,返回选择的option值 后面会废弃，新改进了一个 public String
//	 * selectReturn(String selectLocator) 这个不在需要传入int，自动随机选择并返回
//	 * @param selectLocator 页面元素定位器
//	 * @param option        指定要选择的元素
//	 */
//	public String selectReturn(String selectLocator, int option) {
//		return Selector.selectReturn(selectLocator, option);
//	}
//
//
//	/**
//	 * 选择下拉列表,只需传入select的locator,会在所有option中随机选一个 并且返回选择的option值
//	 * @param selectLocator 页面元素定位器
//	 */
//	public String selectReturn(String selectLocator) {
//		return Selector.selectReturn(selectLocator);
//	}
//
//	/**
//	 * 通过对象的equals方式判断两个变量内容是否相等
//	 * @param expected 预期的值
//	 * @param actual   实际的值
//
//	 */
//	public void assertEquals(String expected, String actual) {
//		Assertion.assertEquals(expected, actual);
//	}
//
//
//
//	/**
//	 * 通过对象的equals方式判断两个整型变量内容是否相等
//	 * @param expected 预期的值
//	 * @param actual   实际的值
//	 */
//	public void assertEquals(int expected, int actual) {
//		Assertion.assertEquals(expected, actual);
//	}
//
//
//
//
//	/***
//	 * 断言指定的目标控件存在
//	 * @param locator 目标控件ID
//	 */
//	public void assertViewExist(String locator) {
//		Assertion.assertViewExist(locator);
//	}
//
//	/**
//	 * 断言condition是否为True
//	 * @param condition 需要被断言的变量
//	 */
//	public void assertTrue(Boolean condition) {
//		Assertion.assertTrue(condition);
//	}
//
//
//	/**
//	 * 断言condition是否为False，不为false则中断执行
//	 * @param condition 需要被断言的变量
//	 */
//	public void assertFalse(Boolean condition) {
//		Assertion.assertFalse(condition);
//	}
//
//	/**
//	 * 断言expected是否被actual包含 比较actual与expected是否相等 不相等则report 测试点FAIL
//	 * @param actual   实际值
//	 * @param expected 期望值
//	 */
//	public void assertContains(String actual, String expected) {
//		Assertion.assertContains(actual, expected);
//	}
//
//
//
//	/**
//	 * 定位器在当前页面能定位到元素的个数,如果没有定位到就返回零.
//	 * @param locator 元素定位器
//	 * @return int 元素个数
//	 */
//	public int getElementCount(String locator) {
//		return AttrFunc.getElementCount(locator);
//	}
//
//
//
//	/**
//	 * 定位器在当前页面能定位到元素的个数,如果没有定位到就返回零.
//	 * @param locator 元素定位器
//	 * @param timeout 定义超时时间 单位秒
//	 * @return 返回元素个数
//	 */
//	public int getElementCount(String locator, int timeout) {
//		return AttrFunc.getElementCount(locator, timeout);
//	}
//
//
//	/***
//	 * 返回符合条件的所有控件
//	 * @param locator
//	 * @return
//	 */
//	public List<WebElement> getElements(String locator){
//		return AttrFunc.getElements(locator);
//	}
//
//
//	/***
//	 * 当目标定位器对应多个控件时取最后一个
//	 * @param locator
//	 * @return
//	 */
//	public WebElement getLastElementInList(String locator){
//		List<WebElement> elementList = getElements(locator);
//		int listLength = elementList.size();
//		return elementList.get(listLength-1);
//	}
//
//
//	/**
//	 * 获取当前页面url
//	 * @return 返回当前的url
//	 */
//	public String getCurrentUrl() {
//		return browser.getCurrentUrl();
//	}
//
//	public void dele(){
//		System.setProperty("webdriver.chrome.driver","tools/chromedriver.exe");
//		DesiredCapabilities caps = DesiredCapabilities.chrome();
//		caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
//
//	}
//	/**
//	 * 获取页面的所有html
//	 * @return 返回当前页面的html源码
//	 */
//	public String getPageSource() {
//		return browser.getPageSource();
//	}
//
//	/**
//	 * 操作当前页面活动元素
//	 * @param value 传递给当前activeElement元素的值
//	 */
//	public void activeElementSendkeys(String value) {
//		browser.activeElementSendkeys(value);
//	}
//
//
//	/**
//	 * 获取当前页面活动元素的值
//	 */
//	public String getActiveElementText() {
//		return browser.getActiveElementText();
//	}
//
//	/**
//	 * 切换到需要操作的iframe
//	 * @param i 当前需要操作的iframe下标 默认从0开始
//	 */
//	public void switchToIframe(int i) {
//		browser.switchToIFrame(i);
//	}
//
//	/**
//	 * 切换到需要操作的iframe
//	 * @param locator frameElement元素定位器
//	 */
//	public void switchToIframe(String locator) {
//		browser.switchToIframe(locator,timeout);
//	}
//
//
//	/**
//	 * 切换到需要操作的iframe
//	 * @param locator frameElement元素定位器
//	 * @param time    定位元素的设定时间值
//	 */
//	public void switchToIframe(String locator,int timeout) {
//		browser.switchToIframe(locator, timeout);
//	}
//
//	/**
//	 * 关闭当前case所有的window
//	 */
//	public void closeAllwindow() {
//		browser.closeAllwindow();
//	}
//
//	/**
//	 * 获取当前windowshandle个数
//	 * @return 返回windowshandle个数
//	 */
//	public int getWindowHandles() {
//		Log.writeLogINFO("go into driver.getWindowHandles().size()");
//		return driver.getWindowHandles().size();
//	}
//
//
//
//	/**
//	 * 等待页面操作完成，跳出当前frame
//	 */
//	public void exitFrame() {
//		browser.exitFrame();
//	}
//
//	/**
//	 * 对跳出的窗口输入文字
//	 * @param value
//	 */
//   public void typeVauleForAlert(String value){
//	   Alert.typeVauleForAlert(value);
//   }
//
//	/**
//	 * 确认弹出框
//	 */
//	public void confirmAlert() {
//		Alert.confirmAlert();
//	}
//
//
//	/***
//	 * 取消弹出框
//	 */
//	public void dismissAlert(){
//		Alert.dismissAlert();
//	}
//
//
//	/***
//	 * 取弹出框文本值
//	 * @return
//	 */
//	public String getAlertText(){
//		return Alert.getAlertText();
//	}
//
//
//
//
//	/**
//	 * 上传附件，包括图片或者其他附件.
//	 * @param locator  元素定位器
//	 * @param filepath 需要上传的文件全路径名 例：d:\\report\\2012-3-15 16-07-21.jpg
//	 */
//	public void uploadFile(String locator, String filepath) {
//		browser.uploadFile(locator, filepath);
//	}
//
//
//	/**
//	 * 上传附件，包括图片或者其他附件.
//	 * @param locator  元素定位器
//	 * @param filepath 需要上传的文件全路径名 例：d:\\report\\2012-3-15 16-07-21.jpg
//	 * @param timeout  超时设置,单位秒.
//	 */
//	public void uploadFile(String locator, String filepath, int timeout) {
//		browser.uploadFile(locator, filepath, timeout);
//	}
//
//
//
//	/**
//	 * 窗口最大化
//	 */
//	public void winMaxSize() {
//		browser.maxSize();
//	}
//
//	/**
//	 * 窗口最小化
//	 */
//	public void winMinSize() {
//		browser.minSize();
//	}
//
//	/**
//	 * 判断当前选项是否被选中(下拉列表,单选或多选)
//	 * @param locator 定位器
//	 * @return 返回当前下拉列表元素是否被选中
//	 */
//	public boolean isSelect(String locator) {
//		return Selector.isSelect(locator);
//	}
//
//
//	/**
//	 * 拖拉一个元素到另一个元素 默认超时时间为config文件中配置的timeout时间 单位秒
//	 * @param sourceLoc 源元素定位器
//	 * @param targetLoc 目标元素定位器
//	 */
//	public void dragAndDrop(String sourceLoc, String targetLoc) {
//		browser.dragAndDrop(sourceLoc, targetLoc,timeout);
//	}
//
//
//	/**
//	 * 拖拉一个元素到另一个元素
//	 * @param sourceLoc 源元素定位器
//	 * @param targetLoc 目标元素定位器
//	 * @param timeout   超时设置,单位秒.
//	 */
//	public void dragAndDrop(String sourceLoc, String targetLoc, int timeout) {
//		browser.dragAndDrop(sourceLoc, targetLoc, timeout);
//	}
//
//
//
//	/**
//	 * 鼠标移动到某个元素上 默认超时时间为config文件中配置的timeout时间 单位秒
//	 * @param locator 元素定位器
//	 */
//	public void moveToElement(String locator) {
//		browser.moveToElement(locator,timeout);
//	}
//
//
//	/**
//	 * 鼠标移动到某个元素上
//	 * @param locator 元素定位器
//	 * @param timeout 超时设置,单位秒.
//	 */
//	public void moveToElement(String locator, int timeout) {
//		browser.moveToElement(locator, timeout);
//	}
//
//
//	/**
//	 * @param locator
//	 */
//	public void moveToElementClick(String locator) {
//		browser.moveToElementClick(locator);
//	}
//
//	/**
//	 * 移动鼠标到指定的元素后通过元素坐标+x和+y的偏移量点击页面区域
//	 *
//	 * @param locator
//	 * @param timeout
//	 */
//	public void moveClick(String locator, int timeout, int xOffset, int yOffset) {
//		browser.moveClick(locator, timeout, xOffset, yOffset);
//	}
//
//	/**
//	 * 删除浏览器当前所有Cookie
//	 */
//	public void deleteAllCookies() {
//		browser.deleteAllCookies();
//	}
//	
//	/**
//	 * 添加cookie
//	 */
//	 public void addCookie(String key,String value){
//		 browser.setCookie(key, value);
//	 }
//
//	/**
//	 * 获取一个指定cookie key的值
//	 * @param key
//	 * @return
//	 */
//	public String getCookie(String key) {
//		return browser.getCookie(key);
//	}
//
//	/**
//	 * 获取当前页面的cookie 并且输出到外部文件
//	 */
//	public String getCookies() {
//		return browser.getCookies();
//	}
//
//	/**
//	 * 获取当前web页面的title
//	 *
//	 * @return 返回当前页面title
//	 */
//	public String getTitle() {
//		return browser.getTitle();
//	}
//
//	/**
//	 * 获取当前元素对象
//	 *
//	 * @param locator
//	 * @param logInfo
//	 * @param timeout
//	 * @return
//	 */
//	public WebElement findElement(String locator, String logInfo, int timeout) {
//		return ElementFinder.findElement(locator, timeout);
//	}
//
//	/**
//	 * 执行js
//	 * @param js
//	 */
//	public void runScript(String js) {
//		browser.runScript(js);
//	}
//
//	/**
//	 * 截屏功能 返回图片名称
//	 */
//	public String printScreen() {
//		return ScreenshotFunc.printScreen();
//	}
//
//	/**
//	 * 刷新页面 keyEvent实现
//	 */
//	public void refreshPage() {
//		browser.refreshPageForKeyEvent();
//	}
//
//
//
//	/**
//	 * 根据指定的name获取cookie的value
//	 * @param name
//	 * @return
//	 */
//	public String getCookieValueByName(String name){
//		return browser.getCookieValue(name);
//	}
//	
//	public void setCookie(String key,String value){
//		browser.setCookie(key, value);
//	}
//
//	/***
//	 * 浏览器driver对象置空
//	 */
//	public void setDriverNull(){
//		BrowserFactory.setDriverNull();
//	}
//
//
//	/***
//	 * 清空指定文本控件内的内容
//	 */
//	public void clear(String locator){
//		ElementOperation.clear(locator);
//	}
//
//
//
//	/***
//	 * 判断目标元素控件是否显示
//	 * @param locator
//	 */
//	public boolean isDisplayed(String locator){
//		return AttrFunc.isDisplayed(locator);
//	}
//
//
//	/***
//	 * 判断目标元素控件可操作状态
//	 * @param locator
//	 * @return
//	 */
//	public boolean isEnabled(String locator){
//		return AttrFunc.isEnabled(locator);
//	}
//
//
//
//	/***
//	 * 向下滚动一屏屏幕
//	 */
//	public void scroolDown(){
//		browser.runScript("window.scrollTo(0,document.body.scrollHeight);");
//	}
//	
//
//	/***
//	 * 向下滚动一屏屏幕
//	 */
//	public void scroolDownHalf(){
//		browser.runScript("window.scrollTo(0,document.body.scrollHeight/2);");
//	}
//
//
//	/***
//	 * 向上滚动一屏屏幕
//	 */
//	public void scroolUp(){
//		.runScript("window.scrollTo(document.body.scrollHeight,0);");
//	}
}