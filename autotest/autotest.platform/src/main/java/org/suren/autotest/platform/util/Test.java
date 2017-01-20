/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.util;

import org.eclipse.wst.sse.core.internal.filebuffers.BasicStructuredDocumentFactory;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.ContentModelManager;
import org.eclipse.wst.xml.core.internal.contentmodel.factory.CMDocumentFactory;
import org.eclipse.wst.xml.ui.internal.wizards.NewXMLGenerator;

/**
 * @author suren
 * @date 2017年1月17日 下午10:49:25
 */
public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		String uri = "file:///D:/Program Files (x86)/Gboat-Toolkit-Suit/workspace_surenpi/autotest.platform/src/main/resources/autotest.web.framework.xsd";
		NewXMLGenerator gen = new NewXMLGenerator();
		gen.setGrammarURI(uri);
		
		String[] err = new String[]{"", ""};
		CMDocument cmDoc = NewXMLGenerator.createCMDocument(gen.getGrammarURI(), err);
		
		gen.setCMDocument(cmDoc);
		gen.createNamespaceInfoList();
		System.out.println(cmDoc);
		
		cmDoc = ContentModelManager.getInstance().createCMDocument(uri, null);
		System.out.println(cmDoc);
		
		System.out.println(gen.getCMDocument());
	}

}
