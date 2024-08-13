import random
from datetime import datetime, timedelta

import mysql.connector
import requests

mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="password",
    database="wemove"
)

myCursor = mydb.cursor()


def generate_consistent_entries(userid, start_date, subDuration):
    date = start_date
    end_date = start_date + timedelta(days=subDuration)

    while date <= end_date:
        randomEntryHour = random.randint(6, 22)
        randomEntryMinute = random.randint(0, 59)
        randomLeaveHour = random.randint(randomEntryHour, 22)
        randomLeaveMinute = random.randint(0, 59)
        entry_time = datetime(date.year, date.month, date.day, randomEntryHour, randomEntryMinute)
        leave_time = datetime(date.year, date.month, date.day, randomLeaveHour, randomLeaveMinute)
        myCursor.execute(insert_INTO_entry, (entry_time, leave_time, userid))
        date += timedelta(days=2)  # We assume that a consistent member trains every two days


def generate_not_very_consistent_entries(userid, start_date, subDuration):
    date = start_date
    end_date = start_date + timedelta(days=subDuration)

    while date <= end_date:
        randomEntryHour = random.randint(6, 22)
        randomEntryMinute = random.randint(0, 59)
        randomLeaveHour = random.randint(randomEntryHour, 22)
        randomLeaveMinute = random.randint(0, 59)
        entry_time = datetime(date.year, date.month, date.day, randomEntryHour, randomEntryMinute)
        leave_time = datetime(date.year, date.month, date.day, randomLeaveHour, randomLeaveMinute)
        myCursor.execute(insert_INTO_entry, (entry_time, leave_time, userid))
        date += timedelta(days=4)


def generate_inconsistent_entries(userid, start_date, subDuration):
    date = start_date
    end_date = start_date + timedelta(days=subDuration)

    while date <= end_date:
        randomEntryHour = random.randint(6, 22)
        randomEntryMinute = random.randint(0, 59)
        randomLeaveHour = random.randint(randomEntryHour, 22)
        randomLeaveMinute = random.randint(0, 59)
        entry_time = datetime(date.year, date.month, date.day, randomEntryHour, randomEntryMinute)
        leave_time = datetime(date.year, date.month, date.day, randomLeaveHour, randomLeaveMinute)
        myCursor.execute(insert_INTO_entry, (entry_time, leave_time, userid))
        date += timedelta(days=15)


query = """
SELECT 
    ms.memberid AS member_id,
    ms.startdate,
    s.name AS subscription_name
FROM 
    member_subscription ms
INNER JOIN 
    subscription s ON ms.subscriptionid = s.id
"""

myCursor.execute(query)
results = myCursor.fetchall()

insert_INTO_entry = "INSERT INTO entry (entryTime,leaveTime, memberid) VALUES (%s, %s,%s)"

yearlySub = []
monthlySub = []
dailySub = []

for row in results:
    if row[2] == "GOLD":
        yearlySub.append([row[0], row[1]])
    elif row[2] == "SILVER":
        monthlySub.append([row[0], row[1]])
    else:
        dailySub.append([row[0], row[1]])

total_goldUsers = len(yearlySub)
consistent_users = int(total_goldUsers * 0.5)
not_very_consistent_users = int(total_goldUsers * 0.3)
inconsistent_users = total_goldUsers - consistent_users - not_very_consistent_users

for goldUser in yearlySub:
    userid = goldUser[0]
    startDate = goldUser[1]
    if consistent_users:
        generate_consistent_entries(userid, startDate, 365)
        consistent_users -= 1
    elif not_very_consistent_users:
        generate_not_very_consistent_entries(userid, startDate, 365)
        not_very_consistent_users -= 1
    else:
        generate_inconsistent_entries(userid, startDate, 365)

total_silverUsers = len(monthlySub)
consistent_users = int(total_silverUsers * 0.5)
not_very_consistent_users = int(total_silverUsers * 0.3)
inconsistent_users = total_silverUsers - consistent_users - not_very_consistent_users

for silverUser in monthlySub:
    userid = silverUser[0]
    startDate = silverUser[1]
    if consistent_users:
        generate_consistent_entries(userid, startDate, 30)
        consistent_users -= 1
    elif not_very_consistent_users:
        generate_not_very_consistent_entries(userid, startDate, 30)
        not_very_consistent_users -= 1
    else:
        generate_inconsistent_entries(userid, startDate, 30)

for bronzeUser in dailySub:
    userid = bronzeUser[0]
    startDate = bronzeUser[1]
    generate_consistent_entries(userid, startDate, 0)

myCursor.close()
mydb.commit()
