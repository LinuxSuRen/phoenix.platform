/**
 * http://surenpi.com
 */
package org.suren.autotest.platform.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author suren
 * @date 2017年1月20日 上午9:33:47
 */
@SuppressWarnings("rawtypes")
public class EnumConverterFactory implements ConverterFactory<String, Enum>
{

	@SuppressWarnings({"unchecked" })
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType)
	{
		return new StringToEnum(targetType);
	}
	
	private class StringToEnum<T extends Enum> implements Converter<String, T> {
    	  
        private final Class<T> enumType;  
  
        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;  
        }  
  
        @SuppressWarnings("unchecked")
		public T convert(String source) {  
            if (source.length() == 0) {  
                return null;  
            }
            
            try
			{
				Method m = this.enumType.getMethod("fromValue", String.class);
				
				return (T) m.invoke(null, source.trim());
			}
			catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
			{
				e.printStackTrace();
			}
            
            return (T) Enum.valueOf(this.enumType, source.trim());  
        }  
    }
}
