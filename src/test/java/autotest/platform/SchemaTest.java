///**
// * http://surenpi.com
// */
//package autotest.platform;
//
//import java.io.File;
//import java.io.Serializable;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBElement;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//
//import org.junit.Test;
//import org.suren.autotest.platform.schemas.Autotest;
//import org.suren.autotest.platform.schemas.EngineType;
//import org.suren.autotest.platform.schemas.EngineTypeDriverEnum;
//import org.suren.autotest.platform.schemas.PageFieldLocator;
//import org.suren.autotest.platform.schemas.PageFieldLocators;
//import org.suren.autotest.platform.schemas.PageFieldType;
//import org.suren.autotest.platform.schemas.PageType;
//
///**
// * @author suren
// * @date 2017年1月19日 上午7:57:11
// */
//public class SchemaTest
//{
//	@Test
//	public void test() throws Exception
//	{
//		JAXBContext context = JAXBContext.newInstance(Autotest.class);
//		Unmarshaller unmarshaller = context.createUnmarshaller();
//		
////		InputStream input = new FileInputStream(new File(""));
//		
//		Autotest autotest = (Autotest) unmarshaller.unmarshal(new File("d:/fujian.xml"));
//		
//		for(PageType page : autotest.getPages().getPage())
//		for(Serializable a : page.getContent())
//		{
//			if(a instanceof JAXBElement)
//			{
//				JAXBElement ele = (JAXBElement) a;
//				System.out.println(ele.getValue().getClass());
//				
//				if(ele.getValue() instanceof PageFieldType)
//				{
//					PageFieldType field = (PageFieldType) ele.getValue();
//					
//					for(Serializable b : field.getContent())
//					{
//						if(b instanceof JAXBElement)
//						{
//							ele = (JAXBElement) b;
//							System.out.println(ele.getValue().getClass());
//							
//							if(ele.getValue() instanceof PageFieldLocators)
//							{
//								PageFieldLocators locators = (PageFieldLocators) ele.getValue();
//								
//								for(PageFieldLocator locator : locators.getLocator())
//								{
//									System.out.println(locator.getValue());
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		
//		autotest.getEngine().setDriver(EngineTypeDriverEnum.CHROME);
//		
//		Marshaller marshaller = context.createMarshaller();
//		marshaller.marshal(autotest, new File("d:/a.xml"));
//	}
//}
