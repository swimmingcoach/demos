#!/usr/bin/python
# -*- coding: UTF-8 -*-
import time;
import logging;

logging.basicConfig(format='%(asctime)s - %(pathname)s[line:%(lineno)d] - %(levelname)s: %(message)s',
                    level=logging.DEBUG)

if __name__ == '__main__':
    logging.debug('START')

    count = 0
    while (count < 10):
        logging.debug(count)
        count = count + 1
        time.sleep(1)

    logging.debug("Good bye!")