package converters;

import org.joda.time.DateTime;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public class DateTimeConverter extends TypeConverter {

	public DateTimeConverter() {
		super(DateTime.class);
	}

	@Override
	public Object encode(Object value, MappedField optionalExtraInfo) {
		DateTime val = (DateTime) value;
		if (val == null)
			return null;
		return val.toString();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object decode(Class targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
		if (fromDBObject == null)
			return null;
		DateTime dec = DateTime.parse((String) fromDBObject);
		return dec;
	}

}