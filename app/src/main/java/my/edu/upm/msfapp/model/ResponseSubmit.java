package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 4/6/2017.
 [
 {
 "ResponseAnswerId":3,
 "Response":{
 "Evaluator":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "Evaluatee":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "ResponseId":51,
 "StartDate":"2017-06-09T00:00:00Z",
 "StartTime":"19:22:25.4138928",
 "Group":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "GroupId":5,
 "Name":"Group 1",
 "WardName":"Ward tingkat 1",
 "CreatedDate":"2017-06-04T05:08:36.3850363Z",
 "LastUpdatedDate":"2017-06-04T05:18:17.5278347Z",
 "MemberList":[
 {
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 {
 "UserId":"c112ba47-3360-4bf0-a183-bd388994c930",
 "UserName":"12312d345678waq9",
 "FullName":"Adzhar",
 "ContactNumber":"0122480514",
 "CreatedDate":"2017-05-27T09:51:53.48Z",
 "Role":5,
 "IsEvaluated":false
 }
 ]
 }
 },
 "Question":{
 "QuestionId":8,
 "Text":"Question 8?",
 "TextBoxFlag":false,
 "CreatedDate":"2017-06-09T16:40:03.9296903Z"
 },
 "ResponseChoice":{
 "Section":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "SectionId":1,
 "Name":"Section 1",
 "Description":"This is the first section",
 "CreatedDate":"2017-05-28T17:19:07.9560452Z",
 "LastUpdatedDate":"2017-05-28T17:19:07.9560452Z",
 "QuestionList":[
 {
 "QuestionId":1,
 "Text":"Question 1",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:41.4504731Z"
 },
 {
 "QuestionId":2,
 "Text":"Question 2",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:45.0717898Z"
 }
 ],
 "IsDelete":false
 },
 "ResponseChoiceId":1,
 "Text":"Yes",
 "Comment":"Comment 1",
 "CreatedDate":"2017-06-04T05:29:41.7409434Z"
 },
 "User":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "Comment":"jshsbsjshsh"
 },
 {
 "ResponseAnswerId":4,
 "Response":{
 "Evaluator":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "Evaluatee":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "ResponseId":51,
 "StartDate":"2017-06-09T00:00:00Z",
 "StartTime":"19:22:25.4138928",
 "Group":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "GroupId":5,
 "Name":"Group 1",
 "WardName":"Ward tingkat 1",
 "CreatedDate":"2017-06-04T05:08:36.3850363Z",
 "LastUpdatedDate":"2017-06-04T05:18:17.5278347Z",
 "MemberList":[
 {
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 {
 "UserId":"c112ba47-3360-4bf0-a183-bd388994c930",
 "UserName":"12312d345678waq9",
 "FullName":"Adzhar",
 "ContactNumber":"0122480514",
 "CreatedDate":"2017-05-27T09:51:53.48Z",
 "Role":5,
 "IsEvaluated":false
 }
 ]
 }
 },
 "Question":{
 "QuestionId":5,
 "Text":"Question 5?",
 "TextBoxFlag":false,
 "CreatedDate":"2017-06-09T15:32:39.7913458Z"
 },
 "ResponseChoice":{
 "Section":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "SectionId":1,
 "Name":"Section 1",
 "Description":"This is the first section",
 "CreatedDate":"2017-05-28T17:19:07.9560452Z",
 "LastUpdatedDate":"2017-05-28T17:19:07.9560452Z",
 "QuestionList":[
 {
 "QuestionId":1,
 "Text":"Question 1",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:41.4504731Z"
 },
 {
 "QuestionId":2,
 "Text":"Question 2",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:45.0717898Z"
 }
 ],
 "IsDelete":false
 },
 "ResponseChoiceId":2,
 "Text":"No",
 "Comment":"Comment 2",
 "CreatedDate":"2017-06-04T05:30:06.8471012Z"
 },
 "User":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 }
 },
 {
 "ResponseAnswerId":5,
 "Response":{
 "Evaluator":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "Evaluatee":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "ResponseId":51,
 "StartDate":"2017-06-09T00:00:00Z",
 "StartTime":"19:22:25.4138928",
 "Group":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "GroupId":5,
 "Name":"Group 1",
 "WardName":"Ward tingkat 1",
 "CreatedDate":"2017-06-04T05:08:36.3850363Z",
 "LastUpdatedDate":"2017-06-04T05:18:17.5278347Z",
 "MemberList":[
 {
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 {
 "UserId":"c112ba47-3360-4bf0-a183-bd388994c930",
 "UserName":"12312d345678waq9",
 "FullName":"Adzhar",
 "ContactNumber":"0122480514",
 "CreatedDate":"2017-05-27T09:51:53.48Z",
 "Role":5,
 "IsEvaluated":false
 }
 ]
 }
 },
 "Question":{
 "QuestionId":3,
 "Text":"Question 3?",
 "TextBoxFlag":false,
 "CreatedDate":"2017-06-09T15:32:19.2216822Z"
 },
 "ResponseChoice":{
 "Section":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "SectionId":1,
 "Name":"Section 1",
 "Description":"This is the first section",
 "CreatedDate":"2017-05-28T17:19:07.9560452Z",
 "LastUpdatedDate":"2017-05-28T17:19:07.9560452Z",
 "QuestionList":[
 {
 "QuestionId":1,
 "Text":"Question 1",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:41.4504731Z"
 },
 {
 "QuestionId":2,
 "Text":"Question 2",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:45.0717898Z"
 }
 ],
 "IsDelete":false
 },
 "ResponseChoiceId":2,
 "Text":"No",
 "Comment":"Comment 2",
 "CreatedDate":"2017-06-04T05:30:06.8471012Z"
 },
 "User":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 }
 },
 {
 "ResponseAnswerId":6,
 "Response":{
 "Evaluator":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "Evaluatee":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "ResponseId":51,
 "StartDate":"2017-06-09T00:00:00Z",
 "StartTime":"19:22:25.4138928",
 "Group":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "GroupId":5,
 "Name":"Group 1",
 "WardName":"Ward tingkat 1",
 "CreatedDate":"2017-06-04T05:08:36.3850363Z",
 "LastUpdatedDate":"2017-06-04T05:18:17.5278347Z",
 "MemberList":[
 {
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 {
 "UserId":"c112ba47-3360-4bf0-a183-bd388994c930",
 "UserName":"12312d345678waq9",
 "FullName":"Adzhar",
 "ContactNumber":"0122480514",
 "CreatedDate":"2017-05-27T09:51:53.48Z",
 "Role":5,
 "IsEvaluated":false
 }
 ]
 }
 },
 "Question":{
 "QuestionId":4,
 "Text":"Question 4?",
 "TextBoxFlag":false,
 "CreatedDate":"2017-06-09T15:32:27.9158099Z"
 },
 "ResponseChoice":{
 "Section":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "SectionId":1,
 "Name":"Section 1",
 "Description":"This is the first section",
 "CreatedDate":"2017-05-28T17:19:07.9560452Z",
 "LastUpdatedDate":"2017-05-28T17:19:07.9560452Z",
 "QuestionList":[
 {
 "QuestionId":1,
 "Text":"Question 1",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:41.4504731Z"
 },
 {
 "QuestionId":2,
 "Text":"Question 2",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:45.0717898Z"
 }
 ],
 "IsDelete":false
 },
 "ResponseChoiceId":2,
 "Text":"No",
 "Comment":"Comment 2",
 "CreatedDate":"2017-06-04T05:30:06.8471012Z"
 },
 "User":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 }
 },
 {
 "ResponseAnswerId":7,
 "Response":{
 "Evaluator":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "Evaluatee":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "ResponseId":51,
 "StartDate":"2017-06-09T00:00:00Z",
 "StartTime":"19:22:25.4138928",
 "Group":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "GroupId":5,
 "Name":"Group 1",
 "WardName":"Ward tingkat 1",
 "CreatedDate":"2017-06-04T05:08:36.3850363Z",
 "LastUpdatedDate":"2017-06-04T05:18:17.5278347Z",
 "MemberList":[
 {
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 {
 "UserId":"c112ba47-3360-4bf0-a183-bd388994c930",
 "UserName":"12312d345678waq9",
 "FullName":"Adzhar",
 "ContactNumber":"0122480514",
 "CreatedDate":"2017-05-27T09:51:53.48Z",
 "Role":5,
 "IsEvaluated":false
 }
 ]
 }
 },
 "Question":{
 "QuestionId":1,
 "Text":"Question 1",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:41.4504731Z"
 },
 "ResponseChoice":{
 "Section":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "SectionId":1,
 "Name":"Section 1",
 "Description":"This is the first section",
 "CreatedDate":"2017-05-28T17:19:07.9560452Z",
 "LastUpdatedDate":"2017-05-28T17:19:07.9560452Z",
 "QuestionList":[
 {
 "QuestionId":1,
 "Text":"Question 1",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:41.4504731Z"
 },
 {
 "QuestionId":2,
 "Text":"Question 2",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:45.0717898Z"
 }
 ],
 "IsDelete":false
 },
 "ResponseChoiceId":1,
 "Text":"Yes",
 "Comment":"Comment 1",
 "CreatedDate":"2017-06-04T05:29:41.7409434Z"
 },
 "User":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 }
 },
 {
 "ResponseAnswerId":8,
 "Response":{
 "Evaluator":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "Evaluatee":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "ResponseId":51,
 "StartDate":"2017-06-09T00:00:00Z",
 "StartTime":"19:22:25.4138928",
 "Group":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "GroupId":5,
 "Name":"Group 1",
 "WardName":"Ward tingkat 1",
 "CreatedDate":"2017-06-04T05:08:36.3850363Z",
 "LastUpdatedDate":"2017-06-04T05:18:17.5278347Z",
 "MemberList":[
 {
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 {
 "UserId":"c112ba47-3360-4bf0-a183-bd388994c930",
 "UserName":"12312d345678waq9",
 "FullName":"Adzhar",
 "ContactNumber":"0122480514",
 "CreatedDate":"2017-05-27T09:51:53.48Z",
 "Role":5,
 "IsEvaluated":false
 }
 ]
 }
 },
 "Question":{
 "QuestionId":7,
 "Text":"Question 7?",
 "TextBoxFlag":false,
 "CreatedDate":"2017-06-09T16:39:54.4398972Z"
 },
 "ResponseChoice":{
 "Section":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "SectionId":1,
 "Name":"Section 1",
 "Description":"This is the first section",
 "CreatedDate":"2017-05-28T17:19:07.9560452Z",
 "LastUpdatedDate":"2017-05-28T17:19:07.9560452Z",
 "QuestionList":[
 {
 "QuestionId":1,
 "Text":"Question 1",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:41.4504731Z"
 },
 {
 "QuestionId":2,
 "Text":"Question 2",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:45.0717898Z"
 }
 ],
 "IsDelete":false
 },
 "ResponseChoiceId":2,
 "Text":"No",
 "Comment":"Comment 2",
 "CreatedDate":"2017-06-04T05:30:06.8471012Z"
 },
 "User":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 }
 },
 {
 "ResponseAnswerId":9,
 "Response":{
 "Evaluator":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "Evaluatee":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "ResponseId":51,
 "StartDate":"2017-06-09T00:00:00Z",
 "StartTime":"19:22:25.4138928",
 "Group":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "GroupId":5,
 "Name":"Group 1",
 "WardName":"Ward tingkat 1",
 "CreatedDate":"2017-06-04T05:08:36.3850363Z",
 "LastUpdatedDate":"2017-06-04T05:18:17.5278347Z",
 "MemberList":[
 {
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 {
 "UserId":"c112ba47-3360-4bf0-a183-bd388994c930",
 "UserName":"12312d345678waq9",
 "FullName":"Adzhar",
 "ContactNumber":"0122480514",
 "CreatedDate":"2017-05-27T09:51:53.48Z",
 "Role":5,
 "IsEvaluated":false
 }
 ]
 }
 },
 "Question":{
 "QuestionId":6,
 "Text":"Question 6?",
 "TextBoxFlag":false,
 "CreatedDate":"2017-06-09T16:39:47.4668366Z"
 },
 "ResponseChoice":{
 "Section":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "SectionId":1,
 "Name":"Section 1",
 "Description":"This is the first section",
 "CreatedDate":"2017-05-28T17:19:07.9560452Z",
 "LastUpdatedDate":"2017-05-28T17:19:07.9560452Z",
 "QuestionList":[
 {
 "QuestionId":1,
 "Text":"Question 1",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:41.4504731Z"
 },
 {
 "QuestionId":2,
 "Text":"Question 2",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:45.0717898Z"
 }
 ],
 "IsDelete":false
 },
 "ResponseChoiceId":2,
 "Text":"No",
 "Comment":"Comment 2",
 "CreatedDate":"2017-06-04T05:30:06.8471012Z"
 },
 "User":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 }
 },
 {
 "ResponseAnswerId":10,
 "Response":{
 "Evaluator":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "Evaluatee":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 "ResponseId":51,
 "StartDate":"2017-06-09T00:00:00Z",
 "StartTime":"19:22:25.4138928",
 "Group":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "GroupId":5,
 "Name":"Group 1",
 "WardName":"Ward tingkat 1",
 "CreatedDate":"2017-06-04T05:08:36.3850363Z",
 "LastUpdatedDate":"2017-06-04T05:18:17.5278347Z",
 "MemberList":[
 {
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 },
 {
 "UserId":"c112ba47-3360-4bf0-a183-bd388994c930",
 "UserName":"12312d345678waq9",
 "FullName":"Adzhar",
 "ContactNumber":"0122480514",
 "CreatedDate":"2017-05-27T09:51:53.48Z",
 "Role":5,
 "IsEvaluated":false
 }
 ]
 }
 },
 "Question":{
 "QuestionId":2,
 "Text":"Question 2",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:45.0717898Z"
 },
 "ResponseChoice":{
 "Section":{
 "Evaluation":{
 "EvaluationId":3,
 "Name":"Evaluation 1",
 "Description":"This is the first evaluation",
 "CreatedDate":"2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate":"2017-05-28T17:18:21.4448286Z"
 },
 "SectionId":1,
 "Name":"Section 1",
 "Description":"This is the first section",
 "CreatedDate":"2017-05-28T17:19:07.9560452Z",
 "LastUpdatedDate":"2017-05-28T17:19:07.9560452Z",
 "QuestionList":[
 {
 "QuestionId":1,
 "Text":"Question 1",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:41.4504731Z"
 },
 {
 "QuestionId":2,
 "Text":"Question 2",
 "TextBoxFlag":true,
 "CreatedDate":"2017-05-28T17:22:45.0717898Z"
 }
 ],
 "IsDelete":false
 },
 "ResponseChoiceId":2,
 "Text":"No",
 "Comment":"Comment 2",
 "CreatedDate":"2017-06-04T05:30:06.8471012Z"
 },
 "User":{
 "UserId":"eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName":"165026",
 "FullName":"Fadzli Name",
 "ContactNumber":"0377000000",
 "CreatedDate":"2017-05-28T03:33:04.68Z",
 "Role":5,
 "IsEvaluated":false
 }
 }
 ]
 */

public class ResponseSubmit {
    @SerializedName("ResponseAnswerId")
    @Expose
    private long ResponseAnswerId;
    @SerializedName("Response")
    @Expose
    private Response Response;
    @SerializedName("Question")
    @Expose
    private Question Question;
    @SerializedName("ResponseChoice")
    @Expose
    private ResponseChoice ResponseChoice;

    @SerializedName("User")
    @Expose
    private User User;

    public void setResponseAnswerId(long responseAnswerId) {
        ResponseAnswerId = responseAnswerId;
    }

    public void setResponse(my.edu.upm.msfapp.model.Response response) {
        Response = response;
    }

    public void setQuestion(my.edu.upm.msfapp.model.Question question) {
        Question = question;
    }

    public void setResponseChoice(my.edu.upm.msfapp.model.ResponseChoice responseChoice) {
        ResponseChoice = responseChoice;
    }

    public long getResponseAnswerId() {
        return ResponseAnswerId;
    }

    public my.edu.upm.msfapp.model.Response getResponse() {
        return Response;
    }

    public my.edu.upm.msfapp.model.Question getQuestion() {
        return Question;
    }

    public my.edu.upm.msfapp.model.ResponseChoice getResponseChoice() {
        return ResponseChoice;
    }

    public my.edu.upm.msfapp.model.User getUser() {
        return User;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

    public static ResponseSubmit fromJson(String json) {
        return Util.getGson().fromJson(json, ResponseSubmit.class);
    }

    public static ArrayList<ResponseSubmit> toList(String json) {
        return Util.getGson().fromJson(json, new TypeToken<ArrayList<ResponseSubmit>>() {
        }.getType());
    }
}
