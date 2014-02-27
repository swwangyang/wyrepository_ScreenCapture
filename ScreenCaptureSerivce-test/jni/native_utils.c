#include <stdio.h>
#include <string.h>
#include "native_log.h"

typedef enum {
	false = 0,
	true = 1,
	null = 2,
} Boolean;

static Boolean sRooted = null;

int isRootAvailable() {
	if(sRooted != null) {
		return sRooted;
	}

	FILE* sh = popen("su", "r");
	if (NULL == sh) {
		LOGW("binary su not found!");
		sRooted = false;
	} else {
		LOGV("binary su found!");
		pclose(sh);
		sh = NULL;
		sRooted = true;
	}

	nativeDebug("%s", (!sRooted ? "not root" : "root"));

	return sRooted;
}

int sudo(const char* fmt, ...) {
	if(!sRooted) {
		return false;
	}

	char cmd[4096] = "su -c \""; // su -c appends command within double quotes
	int len = strlen(cmd);

	const int BUF_SZ = sizeof(cmd) / sizeof(char);
	const int MSG_LEN = BUF_SZ - len - 1;
	char* const va_cmd = cmd + len;

	va_list args;
	va_start(args, va_cmd);
	len += vsnprintf(va_cmd, MSG_LEN, fmt, args);
	va_end(args);

	cmd[len] = '\"'; // append other double quote
	cmd[len + 1] = '\0'; // NUL-terminated

	nativeDebug("/bin/sh: %s", cmd);

	return system(cmd);
}

int checkEndian() {
	union {
		int bytes;
		char lo_byte;
	} test = {0};
	test.bytes = 1;
	return (test.lo_byte == 1);
}
