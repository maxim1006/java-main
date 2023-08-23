### access modifier
public, private

### naming
internal - модели для внутренней логики; нотации в нейминге нет
external - модели, которые мы получаем от других сервисов; добавляется имя сервиса в начало (ServiceOffering, McProductLine)
view - модели, используемые для рендеринга шаблонов; добавляется ViewModel в конец (ProductCardViewModel, BannerViewModel)
dto (можно придумать другое имя) - модели, используемые в наших REST API (в случае идентичности данных можно использовать для отрисовки шаблона, но не наоборот, так как view модели нигде не светятся и требований к ним меньше); добавляется Dto в конец (ProductDetailsDto, CartDto). Внутри viewmodel могут быть dto. они являются контрактом по работе с FE. Dto должны использоваться для передачи FE данных (data-props или window.initialState). но не для рендера страницы. Для рендера их можно использовать при необходимости, если сложные полностью идентичные модели (таких кейсов обычно 1-2 на проект - т.е. это исключение)
entity - работа с БД; добавляется Entity в конец (CacheEntryEntity)

### primitive types
**boolean**, 
byte (-128 до 127 и занимает 1 байт), 
char (одиночный символ в кодировке UTF-16 и занимает 2 байта, поэтому диапазон хранимых значений от 0 до 65535), 
short (-32768 до 32767 и занимает 2 байта), 
**int** (целое число от -2147483648 до 2147483647 и занимает 4 байта), 
**long** (целое число от –9 223 372 036 854 775 808 до 9 223 372 036 854 775 807 и занимает 8 байт), 
float (число с плавающей точкой от -3.4*1038 до 3.4*1038 и занимает 4 байта), 
**double** (число с плавающей точкой от ±4.9*10-324 до ±1.8*10308 и занимает 8 байт)
**new BigDecimal()** используется для денег и тд более precise чем double

### Classes
Фактически все классы наследуются от класса Object. Все остальные классы являются неявно производными от класса Object. Поэтому все типы и классы могут реализовать те методы, которые определены в классе Object.

### helpful
https://unicode-table.com/ru

### Operators
operand + operator + operand (15 + 12)
Remainder/Modulo operator: 4 % 3 = 1 (the remainder of (4 % 3) = 1)

### division
dividend / divisor = quotient

### multiplication
factor(multiplier) * factor(multiplier) = product(multiplication)  

### shortcuts
cmd+option+l - reformat code
ctrl+shift+r - launch main in class
command + option + v - create var

### live templates
sout - System.out.println()
main + shift - main метод в классе

### access modifiers
static, final, public, private, protected

### initialize
числа - с 0 и 0.0
char - \u0000
boolean - false
Object and String - null

### Cache
@CacheInvalidateAll(cacheName = "webContent")
@CacheResult(cacheName = "webContent")

### Exclude props from objects and JSON
https://fasterxml.github.io/jackson-annotations/javadoc/2.9/com/fasterxml/jackson/annotation/JsonInclude.html
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QualificationPriceViewModel {

### Async
там где есть асинхронщина нужно использовать
private final Map<String, String> t = new ConcurrentHashMap<>(); вместо Map<String, String> entities = new HashMap<>();
AtomicBoolean reloaded = new AtomicBoolean(false); вместо boolean