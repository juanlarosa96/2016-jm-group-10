package converters;

import org.joda.time.LocalTime;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public class LocalTimeConverter extends TypeConverter {

	public LocalTimeConverter() {
		super(LocalTime.class);
	}

	@Override
	public Object encode(Object value, MappedField optionalExtraInfo) {
		LocalTime val = (LocalTime) value;
		if (val == null)
			return null;
		return val.toString();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object decode(Class targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
		if (fromDBObject == null)
			return null;
		LocalTime dec = LocalTime.parse((String) fromDBObject);
		return dec;
	}

}
