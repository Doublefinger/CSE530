__author__ = 'Doublefinger'

try:
    import json
except ImportError:
    import simplejson as json
import time
import datetime
import time
import tweepy
from tweepy.parsers import RawParser
import MySQLdb

db = MySQLdb.connect(host= "localhost",
                     user="root",
                     passwd="",
                     db="myDB")
cursor = db.cursor()

def collectData():
    trump =["#MakeAmericaGreatAgain","#trump2016"]
    cruz = ["#cruzcrew","#tedcruz"]
    bernie =["#bernie2016","#feelthebern"]
    clinton = ["#hilaryclinton","#hilary2016"]
    candidates =[trump, cruz, bernie, clinton]

    auth = tweepy.OAuthHandler('', '')
    auth.set_access_token('', '')

    rawParser = RawParser()
    api = tweepy.API(auth_handler=auth)

    count = 0
    dup = 0
    cand_index = 0;
    for cand in candidates:
        cand_index = cand_index+1
        for term in cand:
            for tweet in tweepy.Cursor(api.search,
                                       q=term,
                                       count=100,
                                       result_type="mixed",
                                       include_entities=True,
                                       lang="en").items():
                id=str(tweet.id)
                #contributor=tweet.contributors[0].id_str if tweet.contributors is not None  else None
                #coordinates=str(tweet.coordinates["coordinates"]) if tweet.coordinates is not None else None
                written_at=tweet.created_at
                #               hashtags=tweet.entities["hashtags"]
                favorite_count=tweet.favorite_count
                favorited=int(tweet.favorited)
                place=tweet.place
                text=tweet.text
                text = text.replace(u"\u2018", "'").replace(u"\u2019", "'").encode("UTF-8")
                retweet_count=tweet.retweet_count
                retweeted=int(tweet.retweeted)
                #geo_enabled=int(tweet.user.geo_enabled)

                #Limit of Twitter API
                count = count + 1
                if count >= 17000:
                    count = 0
                    time.sleep(900)
                try:
                    cursor.execute("SELECT COUNT(*) FROM tweets WHERE id=%s", [id])
                    exist = cursor.fetchone()
                    if exist[0] > 0:
                        dup = dup + 1;
                        continue;
                    cursor.execute ("""INSERT INTO tweets (id, written_at, favorite_count, favorited, place, content, retweet_count, retweeted, candidate, created_at) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) """,(id,written_at,favorite_count,favorited,place,text,retweet_count,retweeted,cand_index,datetime.datetime.now()))
                    db.commit()
                except MySQLdb.Error as e:
                    print e
                    #db.rollback()
                    return
    print dup
    db.close()
collectData()
