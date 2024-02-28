type Bar (T);

type Bar (((T)));

type Bar (+5);

type Bar ("ballerina");

type Bar (int);

type Bar (int?);

type Bar (int|float & readonly);

type Bar (int[][]);

type Bar (error<NO_MATCHING_OBJECT>);

type Bar (function ()?);

type Bar ([int, string]);

type Bar (int[1]|[int, int...]);

type Bar (());

type Bar (future<T?>);

type Bar (typedesc<object {}>);

type Bar (stream<string|float>);

type Bar (table<Myrecord> key<MyId>);